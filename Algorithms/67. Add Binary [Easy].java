/**
 * 67. Add Binary
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {



    public String addBinary(String a, String b) {

        if (a.length() < b.length()) return addBinary(b, a);

        StringBuilder sb = new StringBuilder();
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        int temp = 0;

        while (indexB >= 0) {
            int ca = a.charAt(indexA) - '0';
            int cb = b.charAt(indexB) - '0';
            temp += ca + cb;
            sb.append(temp % 2);
            temp /= 2;
            indexA--;
            indexB--;
        }

        while (indexA >= 0) {
            temp += a.charAt(indexA) - '0';
            sb.append(temp % 2);
            temp /= 2;
            indexA--;
        }

        if (temp > 0)
            sb.append(temp);
        return sb.reverse().toString();
    }

    public String addBinary2(String a, String b) {

        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        char[] sumArray = new char[Math.max(a.length(), b.length()) + 1];
        int carry = 0;
        int index = sumArray.length - 1;
        int indexA = charA.length - 1;
        int indexB = charB.length - 1;

        while (indexA >= 0 || indexB >= 0) {

            int aNum = indexA < 0 ? 0 : charA[indexA] - '0';
            int bNum = indexB < 0 ? 0 : charB[indexB] - '0';

            carry += aNum + bNum;
            sumArray[index--] = (char) (carry % 2 + '0');
            carry /= 2;
            indexA--;
            indexB--;
        }

        sumArray[index] = (char) ('0' + carry);
        return carry == 0 ? new String(sumArray, 1, sumArray.length - 1) : new String(sumArray);
    }
}
