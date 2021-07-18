package unraid;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.TwirkBuilder;
import com.stream_pi.action_api.actionproperty.property.Property;
import com.stream_pi.action_api.actionproperty.property.Type;
import com.stream_pi.action_api.externalplugin.NormalAction;
import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.version.Version;
import connect.chat.TwitchChatCredentials;

public class UnraidAction extends NormalAction
{

    private final String channelNameKey = "channel_name_ur";

    private Twirk twirk;

    public UnraidAction()
    {
        setName("Unraid");
        setCategory("Twitch Chat");
        setVisibilityInServerSettingsPane(false);
        setAuthor("j4ckofalltrades");
        setVersion(new Version(1, 0, 0));
        setHelpLink(TwitchChatCredentials.HELP_LINK);
    }

    @Override
    public void initProperties() throws MinorException
    {
        Property channel = new Property(channelNameKey, Type.STRING);
        channel.setDisplayName("Channel");
        channel.setDefaultValueStr("channel_name");
        channel.setCanBeBlank(false);

        addClientProperties(channel);
    }

    @Override
    public void onActionClicked() throws MinorException
    {
        final TwitchChatCredentials.ChatCredentials credentials = TwitchChatCredentials.getCredentials();
        credentials.ensureCredentialsInitialized();

        final String channel = getClientProperties().getSingleProperty(channelNameKey).getStringValue();

        try
        {
            twirk = new TwirkBuilder(channel, credentials.getNickname(), credentials.getOauthToken()).build();
            twirk.connect();
            twirk.channelMessage("/unraid");
        } catch (Exception ex)
        {
            throw new MinorException(
                    "Failed to cancel channel raid",
                    "Could not cancel channel raid, please try again.\n\n"+ex.getMessage());
        }
    }

    @Override
    public void onShutDown() throws MinorException
    {
        if (twirk != null) {
            try
            {
                twirk.disconnect();
            } catch (Exception ex) {
                throw new MinorException("Twitch connection error", ex.getMessage());
            }
        }
    }
}
