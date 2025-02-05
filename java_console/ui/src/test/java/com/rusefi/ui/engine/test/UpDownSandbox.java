package com.rusefi.ui.engine.test;

import com.rusefi.config.generated.Fields;
import com.rusefi.config.generated.Integration;
import com.rusefi.ui.test.WavePanelSandbox;
import com.rusefi.waves.EngineChart;
import com.rusefi.waves.EngineChartParser;
import com.rusefi.waves.EngineReport;
import com.rusefi.core.ui.FrameHelper;
import com.rusefi.ui.engine.UpDownImage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Date: 6/23/13
 * Andrey Belomutskiy, (c) 2013-2020
 * @see WavePanelSandbox
 */
public class UpDownSandbox {
    public UpDownSandbox() {

        String report = "t1!u_9425!0!t2!u_9426!202!t2!d_9427!604!t2!u_9428!956!t2!d_9429!1382!t2!u_9430!1750!t2!d_9431!2204!t2!u_9432!2573!t2!d_9433!3032!t1!d_9434!3160!t2!u_9435!3427!t2!d_9436!3870!t2!u_9437!4251!t2!d_9438!4730!t2!u_9439!5101!t2!d_9440!5593!t2!u_9441!5972!t1!u_9442!6114!t2!d_9443!8007!t2!u_9444!8392!t2!d_9445!8712!t2!u_9446!9031!t2!d_9447!9397!t2!u_9448!9693!t2!d_9449!10077!t2!u_9450!10388!t2!d_9451!10768!t2!u_9452!11067!t2!d_9453!11478!t2!u_9454!11828!t2!d_9455!12218!t2!u_9456!12566!t2!d_9457!12998!t2!u_9458!13438!t2!d_9459!13836!t2!u_9460!14200!t2!d_9461!14655!t2!u_9462!15036!t2!d_9463!15445!t2!u_9464!15815!t2!d_9465!16283!t2!u_9466!16745!t2!d_9467!17099!t1!d_9468!17420!t2!u_9469!17482!t2!d_9470!17958!t2!u_9471!18336!t2!d_9472!18813!t2!u_9473!19194!t2!d_9474!19683!t2!u_9475!20069!t2!d_9476!20463!t1!u_9477!20626!t2!u_9478!22327!t2!d_9479!22775!t2!u_9480!23091!t1!d_9481!23274!t2!d_9482!23452!t2!u_9483!23738!t2!d_9484!24123!t2!u_9485!24424!t2!d_9486!24803!t2!u_9487!25101!t2!d_9488!25502!t1!u_9489!25642!t2!u_9490!25861!t2!d_9491!26230!t2!u_9492!26611!t2!d_9493!26997!t2!u_9494!27434!t2!d_9495!27818!t2!u_9496!28199!t1!d_9497!28486!t2!d_9498!28656!t2!u_9499!29043!t2!d_9500!29444!t2!u_9501!29834!t2!d_9502!30274!t2!u_9503!30646!t1!u_9504!31000!t2!d_9505!31096!t2!u_9506!31532!t2!d_9507!31951!t2!u_9508!32349!t2!d_9509!32809!t2!u_9510!33185!t2!d_9511!33683!t2!u_9512!34087!t1!d_9513!34091!t2!d_9514!36081!t2!u_9515!36401!t2!d_9516!36775!t2!u_9517!37086!t2!d_9518!37447!t2!u_9519!37749!t2!d_9520!38114!t2!u_9521!38419!t2!d_9522!38787!t2!u_9523!39085!t2!d_9524!39476!t2!u_9525!39836!t2!d_9526!40191!t2!u_9527!40517!t2!d_9528!40941!t2!u_9529!41289!t2!d_9530!41747!t2!u_9531!42152!t2!d_9532!42599!t2!u_9533!42956!t2!d_9534!43386!t2!u_9535!43750!t2!d_9536!44203!t2!u_9537!44572!t1!u_9538!44764!t2!d_9539!45040!t2!u_9540!45404!t2!d_9541!45875!t2!u_9542!46253!t2!d_9543!46746!t2!u_9544!47127!t2!d_9545!47602!t2!u_9546!47986!t1!d_9547!48321!t2!d_9548!48388!t2!u_9549!50288!t2!d_9550!50739!t1!u_9551!50785!t2!u_9552!51035!t2!d_9553!51418!t2!u_9554!51759!t2!d_9555!52090!t2!u_9556!52400!t2!d_9557!52769!t2!u_9558!53090!t2!d_9559!53464!t1!d_9560!53565!t2!u_9561!53773!t2!d_9562!54187!t2!u_9563!54529!t2!d_9564!54946!t2!u_9565!55284!t2!d_9566!55755!t1!u_9567!56041!t2!u_9568!56158!t2!d_9569!56599!t2!u_9570!56965!t2!d_9571!57377!t2!u_9572!57753!t2!d_9573!58201!t2!u_9574!58589!t2!d_9575!59025!t1!d_9576!59229!t2!u_9577!59388!t2!d_9578!59866!t2!u_9579!60244!t2!d_9580!60723!t2!u_9581!61085!t2!d_9582!61585!t2!u_9583!61980!t1!u_9584!62170!t2!d_9585!63992!t2!u_9586!64329!t2!d_9587!64694!t2!u_9588!64993!t2!d_9589!65376!t2!u_9590!65707!t2!d_9591!66055!t2!u_9592!66378!t2!d_9593!66745!t2!u_9594!67049!t2!d_9595!67452!t2!u_9596!67799!t2!d_9597!68190!t2!u_9598!68515!t2!d_9599!68967!t2!u_9600!69412!t2!d_9601!69803!t2!u_9602!70196!t2!d_9603!70629!t2!u_9604!70963!t2!d_9605!71415!t2!u_9606!71862!t2!d_9607!72252!t2!u_9608!72636!t2!d_9609!73068!t2!u_9610!73423!t1!d_9611!73453!t2!d_9612!73923!t2!u_9613!74303!t2!d_9614!74780!t2!u_9615!75160!t2!d_9616!75646!t2!u_9617!76090!t2!d_9618!76424!t1!u_9619!76624!t2!u_9620!78352!t2!d_9621!78744!t2!u_9622!79047!t1!d_9623!79265!t2!d_9624!79422!t2!u_9625!79752!t2!d_9626!80096!t2!u_9627!80393!t2!d_9628!80781!t2!u_9629!81089!t2!d_9630!81483!t1!u_9631!81634!t2!u_9632!81861!t2!d_9633!82216!t2!u_9634!82544!t2!d_9635!82988!t2!u_9636!83335!t2!d_9637!83814!t2!u_9638!84194!t1!d_9639!84534!t2!d_9640!84646!t2!u_9641!84990!t2!d_9642!85429!t2!u_9643!85801!t2!d_9644!86260!t2!u_9645!86621!t1!u_9646!87022!t2!d_9647!87080!t2!u_9648!87435!t2!d_9649!87935!t2!u_9650!88312!t2!d_9651!88789!t2!u_9652!89183!t2!d_9653!89663!t2!u_9654!90043!t1!d_9655!90125!t2!d_9656!92056!t2!u_9657!92381!t2!d_9658!92750!t2!u_9659!93053!t2!d_9660!93423!t2!u_9661!93715!t2!d_9662!94088!t2!u_9663!94426!t2!d_9664!94761!t2!u_9665!95090!t2!d_9666!95450!t2!u_9667!95759!t2!d_9668!96166!t2!u_9669!96526!t2!d_9670!96918!t2!u_9671!97282!t2!d_9672!97721!t2!u_9673!98092!t2!d_9674!98574!t2!u_9675!98930!t2!d_9676!99358!t2!u_9677!99736!t2!d_9678!100183!t2!u_9679!100565!t1!u_9680!100720!t2!d_9681!101010!t2!u_9682!101374!t2!d_9683!101852!t2!u_9684!102239!t2!d_9685!102717!t2!u_9686!103089!t2!d_9687!103578!t2!u_9688!104089!t1!d_9689!104293!t2!d_9690!104365!t2!u_9691!106238!t2!d_9692!106687!t1!u_9693!106763!t2!u_9694!106988!t2!d_9695!107362!t2!u_9696!107719!t2!d_9697!108029!t2!u_9698!108360!t2!d_9699!108704!t2!u_9700!108995!t2!d_9701!109394!t1!d_9702!109509!t2!u_9703!109755!t2!d_9704!110112!t2!u_9705!110422!t2!d_9706!110864!t2!u_9707!111221!t2!d_9708!111657!t1!u_9709!111915!t2!u_9710!112088!t2!d_9711!112486!t2!u_9712!112858!t2!d_9713!113332!t2!u_9714!113762!t2!d_9715!114199!t2!u_9716!114762!t2!d_9717!115104!t1!d_9718!115284!t2!u_9719!115508!t2!d_9720!116034!t2!u_9721!116458!t2!d_9722!116998!t2!u_9723!117426!t2!d_9724!118003!t2!u_9725!118428!t1!u_9726!118667!t2!d_9727!120774!t2!u_9728!121118!t2!d_9729!121561!t2!u_9730!122095!t2!d_9731!122321!t2!u_9732!122761!t2!d_9733!123078!";
        EngineChart r = EngineChartParser.unpackToMap(report);

        EngineReport wr = new EngineReport(r.get(Integration.PROTOCOL_CRANK1).toString());

        UpDownImage image = new UpDownImage(wr, "test");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(image, BorderLayout.CENTER);
        new FrameHelper().showFrame(panel);
    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new UpDownSandbox();
            }
        });
    }
}
