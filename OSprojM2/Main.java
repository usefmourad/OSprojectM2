import java.util.*;




public class Main {

    


    public static String Scheduler_FCFS(String input)
    {


        boolean flag = false;

        String res = "";
       
        ArrayList list = new ArrayList<Process>();

        String[] semiColon = input.split(";");
        String[] names = semiColon[0].split(",");
        String[] arrivals = semiColon[1].split(",");
        String[] executes = semiColon[2].split(",");

        int[] arrivalsInt = new int[arrivals.length];

        for(int i = 0; i<arrivalsInt.length;i++)
        {
            arrivalsInt[i] = Integer.parseInt(arrivals[i]);
        }

        Arrays.sort(arrivalsInt);

        for(int i = 0; i<names.length; i++)
        {
            Process p = new Process(names[i], Integer.parseInt(arrivals[i]), Integer.parseInt(executes[i]));
            list.add(p);
        }

        int sameArrivalTime = arrivalsInt[0];

        int k;

        for(k = 0; k<arrivalsInt.length; k++)
        {
            if(arrivalsInt[k] != sameArrivalTime)
            {
                break;
            }
        }

        if(k == arrivalsInt.length)
        {
            flag = true;
        }


        if(flag)
        {
            
            while(!list.isEmpty())
            {
                Process p = (Process) list.get(0);
                res += p.name + "(" + p.execTime + "),";
                list.remove(0);
            }
        }

       

       else
       {

        for(int i = 0; i<arrivalsInt.length; i++)
        {
            for(int j = 0; j<list.size(); j++)
            {
                Process p = (Process) list.get(j);

                if(p.arrivalTime == arrivalsInt[i])
                {
                    res += p.name + "(" + p.execTime + "),";
                    list.remove(j);
                }
            }
        }

    }

        res = res.substring(0, res.length() - 1);

        return res;

    }


    public static String Scheduler_SJF(String input)
    {
        String res = "";

        int totalTime = 0;

        ArrayList list = new ArrayList<Process>();

        String[] semiColon = input.split(";");
        String[] names = semiColon[0].split(",");
        String[] arrivals = semiColon[1].split(",");
        String[] executes = semiColon[2].split(",");


        for(int i = 0; i<names.length; i++)
        {
            Process p = new Process(names[i], Integer.parseInt(arrivals[i]), Integer.parseInt(executes[i]));
            list.add(p);
        }

        int[] arrivalsInt = new int[arrivals.length];

        for(int i = 0; i<arrivalsInt.length;i++)
        {
            arrivalsInt[i] = Integer.parseInt(arrivals[i]);
        }

        Arrays.sort(arrivalsInt);











        res = res.substring(0, res.length() - 1);

        return res;
    }




    public static void main(String[] args) {

        String input = "A,B,C,D,E;0,2,4,5,8;3,6,4,5,2";

        String res = Scheduler_FCFS(input);

        System.out.println(res);
        
    }
    
}

