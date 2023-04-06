import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.LinkedList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;
import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Object obj = new JSONParser().parse(new FileReader("data.json"));
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get("nodes");
            Iterator<JSONObject> it = ja.iterator();
            Account[] accounts = new Account[ja.size()];
            int i = 0;
            while (it.hasNext())
            {
                accounts[i] = new Account(it.next());
                i++;
            }
            SymbolGraph sym = new SymbolGraph(accounts);
            System.out.println("Part 2A\n");
            sym.getNeighbors("giCentre");

            System.out.println("\nPart 2B\nShortest Path from Lane to Rob\n");
            sym.shortestPath("Lane","Rob");

            System.out.println("\nPart 3\n");
            sym.contCounter();
            System.out.println("\n");
            sym.typeByCont();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("Failed");
        }
    }
}
