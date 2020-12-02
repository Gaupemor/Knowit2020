public class Solution_02 {

    static private boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    static public void solve() {
        int befolkning = 5433000;

        int levert = 0;
        for(int i = 0; i <= befolkning; i++) {
            if(Integer.toString(i).contains("7")) {
                int j = i;
                while(!isPrime(j)) j--;
                i += j;
            } else levert++;
        }

        System.out.println(levert);
    }
}
