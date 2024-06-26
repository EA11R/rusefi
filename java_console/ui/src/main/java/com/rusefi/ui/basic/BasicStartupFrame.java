package com.rusefi.ui.basic;

import com.rusefi.Launcher;
import com.rusefi.SerialPortScanner;
import com.rusefi.StartupFrame;
import com.rusefi.autodetect.PortDetector;
import com.rusefi.core.FindFileHelper;
import com.rusefi.core.ui.FrameHelper;
import com.rusefi.io.UpdateOperationCallbacks;
import com.rusefi.maintenance.DfuFlasher;
import com.rusefi.maintenance.ProgramSelector;
import com.rusefi.maintenance.StatusAnimation;
import com.rusefi.maintenance.UpdateStatusWindow;
import com.rusefi.ui.LogoHelper;
import com.rusefi.ui.util.HorizontalLine;
import com.rusefi.ui.util.UiUtils;
import com.rusefi.ui.widgets.ToolButtons;
import org.putgemin.VerticalFlowLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.rusefi.FileLog.isWindows;

/**
 * Focuses on firmware updater
 * Much simpler than {@link com.rusefi.StartupFrame}
 */
public class BasicStartupFrame {
    private final FrameHelper frame;

    public static void runTool(String[] args) {
        new BasicStartupFrame().runTool();
    }

    public BasicStartupFrame() {
        String title = "rusEFI basic console " + Launcher.CONSOLE_VERSION;
        frame = FrameHelper.createFrame(title);
        JPanel panel = new JPanel(new VerticalFlowLayout());
        if (isWindows()) {
            panel.add(ToolButtons.createShowDeviceManagerButton());
            panel.add(StartupFrame.binaryModificationControl());

            JButton update = ProgramSelector.createUpdateFirmwareButton();
            boolean requireBlt = FindFileHelper.isObfuscated();
            if (requireBlt) {
                update.setEnabled(false);

                JLabel noPortsMessage = new JLabel();
                noPortsMessage.setForeground(Color.red);
                panel.add(noPortsMessage);

                StatusAnimation status = new StatusAnimation(new StatusAnimation.StatusConsumer() {
                    @Override
                    public void onStatus(String niceStatus) {
                        noPortsMessage.setText(niceStatus);
                    }
                }, StartupFrame.SCANNING_PORTS);

                SerialPortScanner.INSTANCE.addListener(currentHardware -> SwingUtilities.invokeLater(() -> {
                    status.stop();
                    frame.getFrame().pack();

                    java.util.List<SerialPortScanner.PortResult> ecuPorts =  currentHardware.getKnownPorts().stream().filter(new Predicate<SerialPortScanner.PortResult>() {
                        @Override
                        public boolean test(SerialPortScanner.PortResult portResult) {
                            return portResult.type == SerialPortScanner.SerialPortType.EcuWithOpenblt;
                        }
                    }).collect(Collectors.toList());

                    java.util.List<SerialPortScanner.PortResult> bootloaderPorts =  currentHardware.getKnownPorts().stream().filter(new Predicate<SerialPortScanner.PortResult>() {
                        @Override
                        public boolean test(SerialPortScanner.PortResult portResult) {
                            return portResult.type == SerialPortScanner.SerialPortType.OpenBlt;
                        }
                    }).collect(Collectors.toList());


                    if (!ecuPorts.isEmpty()) {
                        noPortsMessage.setVisible(false);
                        update.setEnabled(true);
                        update.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ProgramSelector.executeJob(update, ProgramSelector.OPENBLT_AUTO, ecuPorts.get(0));
                            }
                        });
                    } else if (!bootloaderPorts.isEmpty()) {
                        noPortsMessage.setVisible(false);
                        update.setEnabled(true);
                        update.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ProgramSelector.executeJob(update, ProgramSelector.OPENBLT_MANUAL, ecuPorts.get(0));
                            }
                        });
                    } else {
                        noPortsMessage.setText("ECU not found");
                    }
                }));
            } else {
                update.addActionListener(e -> DfuFlasher.doAutoDfu(update, PortDetector.AUTO, new UpdateStatusWindow("Update")));
            }
            panel.add(update);
        } else {
            panel.add(new JLabel("Sorry only works on Windows"));
        }

        panel.add(new HorizontalLine());
        JLabel logoLabel = LogoHelper.createLogoLabel();
        if (logoLabel != null)
            panel.add(logoLabel);
        panel.add(LogoHelper.createUrlLabel());

        frame.showFrame(panel, false);
        UiUtils.centerWindow(frame.getFrame());
    }

    private void runTool() {
    }
}
