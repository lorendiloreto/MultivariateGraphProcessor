import java.util.LinkedList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;
import java.io.*;
import java.util.Iterator;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Account {
    public Number id;
    public String name;
    public String screen_name;
    public Number followers_count;
    public Number query_tweet_count;
    public Number friends_count;
    public Number statuses_count;
    public Number listed_count;
    public String utc_offset;
    public Number favourites_count;
    public Number count_followers_in_query;
    public String profile_image_url;
    public boolean influential;
    public boolean original;
    public String memberSince;
    public String location;
    public Number memberFor_days;
    public String type;
    public String continent;
    public String shortName;
    public Number x;
    public Number y;
    public JSONArray neighbors;
    public JSONArray edges;
    public Object userSelectedNeighbors;
    public boolean selected;

    public Account(JSONObject obj)
    {
        id = (Number) obj.get("id");
        name = (String) obj.get("name");
        screen_name = (String) obj.get("screen_name");
        followers_count = (Number) obj.get("followers_count");
        query_tweet_count = (Number) obj.get("query_tweet_count");
        friends_count = (Number) obj.get("friends_count");
        statuses_count = (Number) obj.get("statuses_count");
        listed_count = (Number) obj.get("listed_count");
        utc_offset = (String) obj.get("utc_offset");
        favourites_count = (Number) obj.get("favourites_count");
        count_followers_in_query = (Number) obj.get("count_followers_in_query");
        profile_image_url = (String) obj.get("profile_image_url");
        influential = (boolean) obj.get("influential");
        original = (boolean) obj.get("original");
        memberSince = (String) obj.get("memberSince");
        location = (String) obj.get("location");
        memberFor_days = (Number) obj.get("memberFor_days");
        type = (String) obj.get("type");
        continent = (String) obj.get("continent");
        shortName = (String) obj.get("shortName");
        x = (Number) obj.get("x");
        y = (Number) obj.get("y");
        selected = (boolean) obj.get("selected");
        neighbors = (JSONArray) obj.get("neighbors");
        edges = (JSONArray) obj.get("edges");
    }
}
