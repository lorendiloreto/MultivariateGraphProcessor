import java.util.HashMap;
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
import java.util.Stack;

import org.json.simple.*;
import org.json.simple.parser.*;


public class SymbolGraph
{
    private HashMap<Integer, String> htInvert;
    private HashMap<String, Integer> htName;
    private HashMap<Number, String> htID;
    private HashMap<String, String> htC;
    private String[] keys;
    private Graph G;
    public Account[] accounts;

    public SymbolGraph(Account[] accountArr)
    {
        accounts = accountArr;
        htInvert = new HashMap<Integer, String>();
        int i = 0;
        while (i < accounts.length) {
            htInvert.put(i, accounts[i].shortName);
            i++;
        }
        htName = new HashMap<String, Integer>();
        i = 0;
        while (i < accounts.length) {
            htName.put(accounts[i].shortName, i);
            i++;
        }
        htID = new HashMap<Number, String>();
        i = 0;
        while (i < accounts.length) {
            htID.put(accounts[i].id, accounts[i].shortName);
            i++;
        }
        htC = new HashMap<String, String>();
        i = 0;
        while (i < accounts.length) {
            htC.put(accounts[i].shortName, accounts[i].continent);
            i++;
        }
        keys = new String[htName.size()];
        for (i = 0; i < accounts.length; i++)
            keys[i] = accounts[i].shortName;

        G = new Graph(htName.size());
        for (i = 0; i < accounts.length; i++)
        {
            Number[] neighs = new Number[accounts[i].neighbors.size()];

            for (int j = 0; j < accounts[i].neighbors.size(); j++)
                neighs[j] = (Number) accounts[i].neighbors.get(j);

            for (int j = 0; j < neighs.length; j++)
            {
                G.addEdge(htName.get(accounts[i].shortName), htName.get(htID.get(neighs[j])));
            }
        }
    }

    public boolean contains(String s) {
        return htName.containsKey(s);
    }

    public int index(String s) {
        return htName.get(s);
    }

    public String nameFind(Number s) {
        return htID.get(s);
    }

    public String contFind(String s) {
        return htC.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    public void print()
    {
        System.out.println(accounts[index("Jack")].neighbors);
    }

    public void getNeighbors(String account)
    {
        System.out.println(account + " neighbors (North America):");
        int search = htName.get(account);
        Account target = accounts[search];
        Number[] neighs = neighArray(account);
        String[] nameNeighs = new String[neighs.length];
        for (int i = 0; i < neighs.length; i++)
             nameNeighs[i] = nameFind(neighs[i]);
        for (int i = 0; i < neighs.length; i++)
        {
            if (contFind(nameNeighs[i]).equals("North America"))
            System.out.println(nameNeighs[i]);

        }
    }

    public Number[] neighArray(String a)
    {
        Number[] neighs = new Number[accounts[index(a)].neighbors.size()];
        for (int i = 0; i < neighs.length; i++)
        {
            neighs[i] = (Number) accounts[index(a)].neighbors.get(i);
        }
        return neighs;
    }

    public void shortestPath(String first, String second)
    {
        int a = htName.get(first);
        int b = htName.get(second);
        Account n1 = accounts[a];
        Account n2 = accounts[b];
        BreadthFirstPaths bfPath = new BreadthFirstPaths(G, a);
        Stack<Integer> path = (Stack<Integer>) bfPath.pathTo(b);
        System.out.println(path);
        if(path != null)
        {
            for(int i = 0; i < path.size(); i++)
            {
                System.out.println(htInvert.get(path.get(i)));
            }
        }
    }

    public void contCounter()
    {
        int NA = 0;
        int SA = 0;
        int EU = 0;
        int AS = 0;

        for (int i = 0; i < accounts.length; i++)
        {
            if (accounts[i].continent.equals("North America")) { NA++; }
            else if (accounts[i].continent.equals("South America")) { SA++; }
            else if (accounts[i].continent.equals("Europe")) { EU++; }
            else if (accounts[i].continent.equals("Asia")) { AS++; }
        }
        System.out.println("Accounts by continent:");
        System.out.println("North America: " + NA);
        System.out.println("South America: " + SA);
        System.out.println("Europe: " + EU);
        System.out.println("Asia: " + AS);
    }

    public void typeByCont()
    {
        int NAP = 0;
        int SAP = 0;
        int EUP = 0;
        int ASP = 0;
        int NAI = 0;
        int SAI = 0;
        int EUI = 0;
        int ASI = 0;

        for (int i = 0; i < accounts.length; i++)
        {
            if (accounts[i].continent.equals("North America") && accounts[i].type.equals("person")) { NAP++; }
            else if (accounts[i].continent.equals("South America") && accounts[i].type.equals("person")) { SAP++; }
            else if (accounts[i].continent.equals("Europe") && accounts[i].type.equals("person")) { EUP++; }
            else if (accounts[i].continent.equals("Asia") && accounts[i].type.equals("person")) { ASP++; }
            else if (accounts[i].continent.equals("North America") && accounts[i].type.equals("institution")) { NAI++; }
            else if (accounts[i].continent.equals("South America") && accounts[i].type.equals("institution")) { SAI++; }
            else if (accounts[i].continent.equals("Europe") && accounts[i].type.equals("institution")) { EUI++; }
            else if (accounts[i].continent.equals("Asia") && accounts[i].type.equals("institution")) { ASI++; }
        }
        System.out.println("Accounts by continent and type of account (Person vs. Institution):");
        System.out.println("People from North America: " + NAP);
        System.out.println("People from South America: " + SAP);
        System.out.println("People from Europe: " + EUP);
        System.out.println("People from Asia: " + ASP);
        System.out.println("Institutions from North America: " + NAI);
        System.out.println("Institutions from South America: " + SAI);
        System.out.println("Institutions from Europe: " + EUI);
        System.out.println("Institutions from Asia: " + ASI);
    }
}