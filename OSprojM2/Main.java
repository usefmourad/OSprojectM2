import java.util.*;
import java.util.Queue;

public class Main {

    public static String Scheduler_FCFS(String input) {

        boolean flag = false;

        String res = "";

        ArrayList list = new ArrayList<Process>();

        String[] semiColon = input.split(";");
        String[] names = semiColon[0].split(",");
        String[] arrivals = semiColon[1].split(",");
        String[] executes = semiColon[2].split(",");

        int[] arrivalsInt = new int[arrivals.length];

        for (int i = 0; i < arrivalsInt.length; i++) {
            arrivalsInt[i] = Integer.parseInt(arrivals[i]);
        }

        Arrays.sort(arrivalsInt);

        for (int i = 0; i < names.length; i++) {
            Process p = new Process(names[i], Integer.parseInt(arrivals[i]), Integer.parseInt(executes[i]));
            list.add(p);
        }

        int sameArrivalTime = arrivalsInt[0];

        int k;

        for (k = 0; k < arrivalsInt.length; k++) {
            if (arrivalsInt[k] != sameArrivalTime) {
                break;
            }
        }

        if (k == arrivalsInt.length) {
            flag = true;
        }

        if (flag) {

            while (!list.isEmpty()) {
                Process p = (Process) list.get(0);
                res += p.name + "(" + p.execTime + "),";
                list.remove(0);
            }
        }

        else

        {

            for (int i = 0; i < arrivalsInt.length; i++) {
                for (int j = 0; j < list.size(); j++) {
                    Process p = (Process) list.get(j);

                    if (p.arrivalTime == arrivalsInt[i]) {
                        res += p.name + "(" + p.execTime + "),";
                        list.remove(j);
                    }
                }
            }
        }

        res = res.substring(0, res.length() - 1);

        return res;

    }

    public static String Scheduler_SJF(String input) {
        String res = "";

        int currentTime = 0;

        ArrayList list = new ArrayList<Process>();

        String[] semiColon = input.split(";");
        String[] names = semiColon[0].split(",");
        String[] arrivals = semiColon[1].split(",");
        String[] executes = semiColon[2].split(",");

        for (int i = 0; i < names.length; i++) {
            Process p = new Process(names[i], Integer.parseInt(arrivals[i]), Integer.parseInt(executes[i]));
            list.add(p);
        }

        int[] arrivalsInt = new int[arrivals.length];

        for (int i = 0; i < arrivalsInt.length; i++) {
            arrivalsInt[i] = Integer.parseInt(arrivals[i]);
        }

        Arrays.sort(arrivalsInt);

        while (!list.isEmpty()) {

            ArrayList sameExecList = new ArrayList<Integer>();

            for (int i = 0; i < list.size(); i++) {
                Process p = (Process) list.get(i);

                if (p.arrivalTime <= currentTime) {
                    sameExecList.add(p.execTime);
                }
            }

            Collections.sort(sameExecList);

            while (!sameExecList.isEmpty()) {
                int min = (int) sameExecList.get(0);

                for (int i = 0; i < list.size(); i++) {
                    Process p = (Process) list.get(i);
                    if (p.execTime == min) {
                        res += p.name + "(" + p.execTime + "),";
                        list.remove(i);
                        sameExecList.remove(0);
                        currentTime += min;
                        break;
                    }
                }
            }
        }

        res = res.substring(0, res.length() - 1);

        return res;
    }

    public static String Scheduler_RR(String input) {
        String res = "";
        ArrayList list = new ArrayList<Process>();

        String[] semiColon = input.split(";");
        String[] names = semiColon[0].split(",");
        String[] arrivals = semiColon[1].split(",");
        String[] executes = semiColon[2].split(",");

        for (int i = 0; i < names.length; i++) {
            Process p = new Process(names[i], Integer.parseInt(arrivals[i]), Integer.parseInt(executes[i]));
            list.add(p);

        }

        int[] arrivalsInt = new int[arrivals.length];
        int currentTime = 0;
        int max = 0;
        int qt = 2;
        int count = 0;
        Queue<Process> q = new LinkedList<>();

        while (true) {
            boolean x = false;
            if (q.isEmpty()) {
                x = check_new(currentTime, list, max, q);
                while (x == true) {
                    max++;
                    x = check_new(currentTime, list, max, q);

                }
            }
            while (!q.isEmpty()) {
                if (q.peek().execTime > qt) {
                    Process t = q.poll();
                    currentTime = currentTime + qt;
                    t.execTime -= qt;
                    x = check_new(currentTime, list, max, q);
                    while (x == true) {
                        max++;
                        x = check_new(currentTime, list, max, q);
                    }
                    q.add(t);
                    res += t.name + "(" + qt + "),";

                } else {
                    res += q.peek().name + "(" + q.peek().execTime + "),";
                    currentTime = currentTime + q.peek().execTime;
                    q.remove();
                    count++;
                    x = check_new(currentTime, list, max, q);
                    while (x == true) {
                        max++;
                        x = check_new(currentTime, list, max, q);
                    }
                }
            }
            if (count == list.size()) {
                break;
            }

        }

        res = res.substring(0, res.length() - 1);

        return res;

    }

    public static boolean check_new(int t, ArrayList<Process> list, int max, Queue<Process> q) {
        if (max < list.size() && t >= list.get(max).arrivalTime) {
            q.add(list.get(max));
            return true;

        }
        return false;
    }

    public static void main(String[] args) {

        String input = "A,B,C,D,E;0,2,4,5,8;3,6,4,5,2";

        String res = Scheduler_RR(input);

        System.out.println(res);

    }

}
