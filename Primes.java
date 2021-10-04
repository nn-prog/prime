import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Primes {

    int num = 1;


    public Integer next() {
        if (num == Integer.MAX_VALUE) {
            return null;
        }
        if (num == 1) {
            num = num + 1;
            return num;
        }
        if (num == 2) {
            num = num + 1;
            return num;
        }
        num = num + 2;
        while (!isPrime()) {
            num = num + 2;
        }
        return num;
    }

    private boolean isPrime() {
        for (int i = 2; i <= num / 2; i = i + 1) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Primes primes = new Primes();
        Integer prevPrime = primes.next();
        Integer prime = primes.next();
        int maxDiff = 0;
        String property = System.getProperty("user.home");
        System.out.println(property);
        List<String> lines = new ArrayList<>();
        TreeSet<Integer> diffs = new TreeSet<>();
        lines.add("Previous Prime, Prime, Diff, Ratio");
        for (int i = 0; i < 10000; i++) {
            if (prime != null) {
                int diff = prime - prevPrime;
                diffs.add(diff);
                String line = prevPrime + ", " + prime + ", " + diff+", "+(prime/(double)prevPrime);
                lines.add(line);
                prevPrime = prime;
                prime = primes.next();
                if (maxDiff < diff) {
                    maxDiff = diff;
                }
            }
        }
        System.out.println("max diff " + maxDiff);
        System.out.println("Unique diffs " + (diffs.size()-1));
        for (Integer diff : diffs) {
            while(diff/2>0){
                System.out.print(" "+diff);
                diff = diff/2;
            }
            System.out.println();
        }
        System.out.println();
        Files.write(Paths.get(property + "/primes_diff.csv"), lines, StandardCharsets.UTF_8);
    }
}


