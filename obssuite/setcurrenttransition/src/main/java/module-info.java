module com.stream_pi.obssuite.setcurrenttransition
{
    requires com.stream_pi.actionapi;
    requires com.stream_pi.util;

    requires obs.websocket.java;
    requires com.stream_pi.obssuite.motheraction;

    provides com.stream_pi.actionapi.normalaction.NormalAction with setcurrenttransition.SetCurrentTransition;
}
