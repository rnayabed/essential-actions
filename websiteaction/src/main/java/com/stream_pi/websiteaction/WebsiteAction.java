package com.stream_pi.websiteaction;

import com.stream_pi.action_api.api.NormalAction;
import com.stream_pi.action_api.property.StringProperty;
import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.version.Version;

import java.awt.*;
import java.net.URI;

public class WebsiteAction extends NormalAction
{
    public WebsiteAction()
    {
        setName("Website");
        setCategory("Essentials");
        setAuthor("rnayabed");
        setServerButtonGraphic("fas-globe");
        setHelpLink("https://github.com/stream-pi/essentialactions");
        setVersion(new Version(1,0,1));
    }

    @Override
    public void initProperties() throws MinorException
    {
        StringProperty websiteUrl = new StringProperty("websiteURL");
        websiteUrl.setDisplayName("Website URL");
        websiteUrl.setDefaultValueStr("https://stream-pi.com/");
        websiteUrl.setCanBeBlank(false);

        addClientProperties(websiteUrl);
    }

    @Override
    public void onActionClicked() throws MinorException
    {
        String urlToOpen = getClientProperties().getSingleProperty("websiteURL").getStringValue();


        if(!urlToOpen.startsWith("https://") && !urlToOpen.startsWith("http://"))
        {
            urlToOpen = "https://" + urlToOpen;
        }

        try
        {
            Desktop.getDesktop().browse(new URI(urlToOpen));
        }
        catch (Exception e)
        {
            throw new MinorException("Unable to open URL '"+urlToOpen+"'. Check if its correct.");
        }
    }
}
