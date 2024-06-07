package OER_TPN_LAB;

import Components.*;
import DataObjects.DataInteger;
import DataObjects.DataREL;
import DataObjects.DataRELQueue;
import DataObjects.DataTransfer;
import DataOnly.REL;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

import java.util.ArrayList;

public class Supervisor {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Supervisor Petri";
        pn.NetworkPort = 1082;

        DataREL ps_i1 = new DataREL();
        ps_i1.SetName("ps_i1");
        pn.PlaceList.add(ps_i1);

        DataRELQueue ps_1 = new DataRELQueue();
        ps_1.SetName("ps_1");
        ps_1.Value.Size=10;


        pn.PlaceList.add(ps_1);

        DataTransfer ps_o1 = new DataTransfer();
        ps_o1.SetName("ps_o1");
        ps_o1.Value = new TransferOperation("localhost", "1080", "p_i1");
        pn.PlaceList.add(ps_o1);

        DataInteger ps_2 = new DataInteger();
        ps_2.SetName("ps_2");
        ps_2.SetValue(0);
        pn.PlaceList.add(ps_2);

        DataInteger ps_3 = new DataInteger();
        ps_3.SetName("ps_3");
        ps_3.SetValue(0);
        pn.PlaceList.add(ps_3);

        DataInteger ps_i2 = new DataInteger();
        ps_i2.SetName("ps_i2");
        pn.PlaceList.add(ps_i2);

        DataInteger ps_o2 = new DataInteger();
        ps_o2.SetName("ps_o2");
        pn.PlaceList.add(ps_o2);

        //-------------------places to work with----------------------------
        DataInteger Halt = new DataInteger();
        Halt.SetName("Halt");
        Halt.SetValue(0);
        pn.ConstantPlaceList.add(Halt);

        DataInteger Right = new DataInteger();
        Right.SetName("Right");
        Right.SetValue(1);
        pn.ConstantPlaceList.add(Right);

        DataInteger Left = new DataInteger();
        Left.SetName("Left");
        Left.SetValue(-1);
        pn.ConstantPlaceList.add(Left);

        // --------------------Ts_1-----------------------------------------
        PetriTransition ts_1 = new PetriTransition(pn);
        ts_1.TransitionName = "ts_1";
        ts_1.InputPlaceName.add("ps_i1");

        Condition T1Ct1 = new Condition(ts_1, "ps_i1", TransitionCondition.NotNull);
        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;


        grdT1.Activations.add(new Activation(ts_1, "ps_i1", TransitionOperation.AddElement, "ps_1"));
        ts_1.GuardMappingList.add(grdT1);

        ts_1.Delay = 0;
        pn.Transitions.add(ts_1);


        // ----------------------------------Ts_2-----------------------------------------
        PetriTransition ts_2 = new PetriTransition(pn);
        ts_2.TransitionName = "ts_2";
        ts_2.InputPlaceName.add("ps_1");
        ts_2.InputPlaceName.add("ps_2");
        ts_2.InputPlaceName.add("ps_3");

        Condition T2Ct1 = new Condition(ts_2, "ps_1", TransitionCondition.HaveREL);
        Condition T2Ct2 = new Condition(ts_2, "ps_2", TransitionCondition.NotNull);
        Condition T2Ct3 = new Condition(ts_2, "ps_3", TransitionCondition.NotNull);
        T2Ct2.SetNextCondition(LogicConnector.AND, T2Ct3);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        ArrayList<String> as= new ArrayList<>();
        as.add("ps_o1");as.add("ps_3");
        grdT2.Activations.add(new Activation(ts_2, "ps_1", TransitionOperation.SendROverNetwork, "ps_o1"));
        grdT2.Activations.add(new Activation(ts_2, "ps_1", TransitionOperation.PopElement_R_E,as));
        ts_2.GuardMappingList.add(grdT2);

        ts_2.Delay = 0;
        pn.Transitions.add(ts_2);

        // --------------------Ts_3-----------------------------------------
        PetriTransition ts_3 = new PetriTransition(pn);
        ts_3.TransitionName = "ts_3";
        ts_3.InputPlaceName.add("ps_i2");

        Condition T3Ct1 = new Condition(ts_3, "ps_i2", TransitionCondition.NotNull);
        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        grdT3.Activations.add(new Activation(ts_3, "ps_i2", TransitionOperation.Move, "ps_2"));
        grdT3.Activations.add(new Activation(ts_3, "ps_i2", TransitionOperation.Move, "ps_o2"));
        ts_3.GuardMappingList.add(grdT3);

        ts_3.Delay = 0;
        pn.Transitions.add(ts_3);



        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 3000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
