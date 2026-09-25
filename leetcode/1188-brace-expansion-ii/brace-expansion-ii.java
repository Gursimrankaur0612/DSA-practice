import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> resultSet = parse(expression);
        return new ArrayList<>(resultSet);
    }

    private Set<String> parse(String expr) {
        Set<String> res = new TreeSet<>();
        
        // Step 1: Handle top-level commas (Union)
        List<String> commaParts = splitByTopLevelCommas(expr);
        if (commaParts.size() > 1) {
            for (String part : commaParts) {
                res.addAll(parse(part));
            }
            return res;
        }

        // Step 2: Handle concatenation (Cartesian Product)
        List<Set<String>> factors = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '{') {
                int j = i, braceCount = 0;
                while (j < expr.length()) {
                    if (expr.charAt(j) == '{') braceCount++;
                    if (expr.charAt(j) == '}') braceCount--;
                    if (braceCount == 0) break;
                    j++;
                }
                // Recursively parse inner expression within braces
                factors.add(parse(expr.substring(i + 1, j)));
                i = j + 1;
            } else {
                int j = i;
                while (j < expr.length() && Character.isLowerCase(expr.charAt(j))) {
                    j++;
                }
                Set<String> singleLetterSet = new HashSet<>();
                singleLetterSet.add(expr.substring(i, j));
                factors.add(singleLetterSet);
                i = j;
            }
        }

        // Combine factors using Cartesian Product
        res.add("");
        for (Set<String> factor : factors) {
            Set<String> nextRes = new TreeSet<>();
            for (String prefix : res) {
                for (String word : factor) {
                    nextRes.add(prefix + word);
                }
            }
            res = nextRes;
        }

        return res;
    }

    private List<String> splitByTopLevelCommas(String expr) {
        List<String> parts = new ArrayList<>();
        int braceCount = 0;
        int start = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '{') braceCount++;
            else if (c == '}') braceCount--;
            else if (c == ',' && braceCount == 0) {
                parts.add(expr.substring(start, i));
                start = i + 1;
            }
        }
        parts.add(expr.substring(start));
        return parts;
    }
}