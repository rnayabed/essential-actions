module com.stream_pi.hotkeyaction
{
    requires com.stream_pi.actionapi;
    requires com.stream_pi.util;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;

    requires java.desktop;

    provides com.stream_pi.actionapi.normalaction.NormalAction with com.stream_pi.hotkeyaction.HotkeyAction;
}