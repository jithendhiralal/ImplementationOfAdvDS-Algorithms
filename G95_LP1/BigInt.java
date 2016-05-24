
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BigInt {

    private LinkedList value;

    public LinkedList getValue() {
        LinkedList<Long> temp = new LinkedList<>(value);
        return temp;
    }

    public void setValue(LinkedList val) {
        this.value = val;
    }

    final int B = 10;

    //Constructor
    public BigInt() {
        value = new LinkedList();
    }

    //Constructor 
    public BigInt(LinkedList val) {
        this.value = val;
    }

    /**
     * Construct to extract number from the string and store it in the list
     *
     * @param s : Number in String format
     */
    BigInt(String s) {
        this(Long.parseLong(s));
        this.value = new LinkedList();
        boolean isNegative = false;
        int length = s.length() - 1;
        if (s.charAt(0) == '-') {
            isNegative = true;
        }

        for (int i = length; i >= 0; i--) {
            long digit = s.charAt(i) - '0';
            if (digit >= 0 && digit <= 9) {
                this.value.add(digit);
            }
        }
        if (isNegative) {
            changeSign(this);

        }

    }

    /**
     * Construct to extract number from the long and store it in the list
     *
     * @param num
     */
    BigInt(Long num) {

        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num = -1 * num;
        }

        this.value = new LinkedList();
        while (num > 0) {
            this.value.add(num % B);
            num /= B;
        }
        if (isNegative) {
            changeSign(this);
        }

    }

    /**
     * This method perform sum of two numbers stored as BigInt
     *
     * @param a : BigInt object
     * @param b : BigInt object
     * @return : return BigInt Object
     */
    public BigInt add(BigInt a, BigInt b) {

        BigInt result = new BigInt();
        Iterator itr1 = a.value.iterator();
        Iterator itr2 = b.value.iterator();
        Long x1 = next(itr1);
        Long x2 = next(itr2);
        long carry = 0, sum = 0;

        while (x1 != null && x2 != null) {
            sum = x1 + x2 + carry;
            result.value.add(sum % B);
            carry = sum / B;
            x1 = next(itr1);
            x2 = next(itr2);
        }
        //Adding the remaining numbers to the list
        while (x1 != null) {
            sum = x1 + carry;
            result.value.add(sum % B);
            carry = sum / B;
            x1 = next(itr1);
        }
        while (x2 != null) {
            sum = x2 + carry;
            result.value.add(sum % B);
            carry = sum / B;
            x2 = next(itr2);
        }
        //Adding the carry bit in case of any to the list
        if (carry > 0) {
            result.value.add(carry);
        }
        return result;

    }

    /**
     * This method perform subtraction of two numbers stored as BigInt
     *
     * @param a : BigInt object
     * @param b : BigInt object
     * @return : return BigInt Object
     */
    public BigInt subtract(BigInt a, BigInt b) {
        int sign_flag;
        BigInt temp1 = new BigInt(a.getValue());
        BigInt temp2 = new BigInt(b.getValue());

        trim(temp1);
        trim(temp2);

        sign_flag = compare(temp1, temp2);
        if (sign_flag == 0) {
            BigInt zero = new BigInt("0");
            return zero;
        }
        if (sign_flag == -1) {
            BigInt temp = a;
            a = b;
            b = temp;
        }
        BigInt result = new BigInt();

        Iterator itr1 = a.value.iterator();
        Iterator itr2 = b.value.iterator();
        Long x = next(itr1);
        Long y = next(itr2);

        // Flag to check if borrow has to be done from the previous digit or not
        Boolean borrow = false;

        while (x != null && y != null) {
            if (borrow) {
                if (x == 0) {
                    x = x + B;
                    x = x - 1;
                } else {
                    x = x - 1;

                    borrow = false;
                }
            }
            if (x < y) {
                borrow = true;
                x = x + B;
                result.value.add(x - y);
            } else {
                result.value.add(x - y);
            }
            x = next(itr1);
            y = next(itr2);
        }
        //Considering the remaining entries to the list
        while (x != null) {
            if (borrow) {
                if (x == 0) {
                    x = x + B;
                    x = x - 1;
                } else {
                    x = x - 1;
                    borrow = false;
                }
            }
            result.value.add(x);
            x = next(itr1);
        }
        trim(result);
        if (sign_flag == -1) {
            changeSign(result);

        }
        return result;

    }
    
    /**
     * 
     * @param a1
     * @param b1
     * @return 
     */
    private int compare(BigInt a1, BigInt b1) {
        BigInt temp1 = new BigInt(a1.getValue());
        BigInt temp2 = new BigInt(b1.getValue());
        trim(temp1);
        trim(temp2);
        Collections.reverse(temp1.value);
        Collections.reverse(temp2.value);

        if (temp1.value.size() < temp2.value.size()) {
            return -1;
        }
        if (temp1.value.size() > temp2.value.size()) {
            return 1;
        }
        Iterator itr1 = temp1.value.iterator();
        Iterator itr2 = temp2.value.iterator();
        Long x = next(itr1);
        Long y = next(itr2);

        while (x != null && y != null) {
            if (x < y) {
                return -1;
            } else if (x > y) {
                return 1;
            }
            x = next(itr1);
            y = next(itr2);
        }
        return 0;
    }

    private void trim(BigInt a) {
        int recent = 0;
        int count = 0;

        Iterator itr = a.value.iterator();
        Long x = next(itr);
        while (x != null) {
            count++;
            if (x != 0L) {
                recent = count;
            }

            x = next(itr);
        }
        int delete = count - recent;
        for (int i = 0; i < delete; i++) {
            a.value.removeLast();
        }
    }

    private void resizeBigInt(BigInt a, BigInt b) {
        //size of a and b Bigint
        int aSize = a.value.size();
        int bSize = b.value.size();

        if (aSize % 2 != 0) {
            a.value.addLast(0L);
        }

        if (bSize % 2 != 0) {
            b.value.addLast(0L);
        }

        aSize = a.value.size();
        bSize = b.value.size();

        int count;
        if (aSize != bSize) {

            if (aSize > bSize) {
                count = aSize - bSize;
                while (count > 0) {
                    b.value.addLast(0L);
                    count--;
                }
            } else {
                count = bSize - aSize;
                while (count > 0) {
                    a.value.addLast(0L);
                    count--;
                }
            }
        }

    }
    
    /**
     * 
     * @param a
     * @param b
     * @return 
     */
    public BigInt product(BigInt a, BigInt b) {

        //size of a and b Bigint
        int aSize = a.value.size();
        int bSize = b.value.size();

        if ((aSize == 1) && (bSize == 1)) {
            Iterator itr1 = a.value.iterator();
            Iterator itr2 = b.value.iterator();
            Long x = next(itr1);
            Long y = next(itr2);

            Long res = x * y;
            BigInt result = new BigInt(res);
            return result;
        }

        //if Needed will resize LinkedList
        resizeBigInt(a, b);

        //Get size of a and b after resize
        aSize = a.value.size();
        bSize = b.value.size();
        int N = Math.max(aSize, bSize);

        // Create and split
        BigInt a1 = new BigInt();
        BigInt a2 = new BigInt();
        splitList(a, a1, a2);

        BigInt b1 = new BigInt();
        BigInt b2 = new BigInt();
        splitList(b, b1, b2);

        BigInt subProduct1 = product(a1, b1);
        BigInt subProduct2 = product(add(a1, a2), add(b1, b2));
        BigInt subProduct3 = product(a2, b2);

        BigInt subResult1 = shift(subtract(subProduct2, add(subProduct1, subProduct3)), N / 2);
        BigInt subResult2 = shift(subProduct3, N);
        BigInt endResult = add(add(subProduct1, subResult1), subResult2);

        return endResult;
    }

    /**
     * 
     * @param a
     * @param n
     * @return 
     */
    public BigInt shift(BigInt a, int n) {
        for (int i = 1; i <= n; i++) {
            a.value.addFirst(0L);
        }
        return a;
    }

    /**
     * This method Calculate power for BigInt for base in Long
     * @param a : BigInt number
     * @param b : Long number
     * @return
     */
    public BigInt power(BigInt a, long b) {
        BigInt result = new BigInt();
        
        if(b == 1){
            return a;
        }
        if(b == 0){
            return new BigInt(1L);
        }
        BigInt half = power(a,b/2);
        result = product(half, half);
        if(b%2!= 0){
            return product(result,a);
        }
        else{
            return result;
        }
        
    }

    /**
     * This method calculate power for base in BignInt
     *
     * @param a : BigInt number
     * @param base : BigInt number
     * @return
     */
    public BigInt power(BigInt a, BigInt base) {
        BigInt result = new BigInt();
        BigInt zero = new BigInt("0");
        BigInt two = new BigInt(2L);
        BigInt aTemp = new BigInt(a.getValue());
        BigInt baseTemp = new BigInt(base.getValue());
        
        BigInt endResult = powerUtil(a, base, result);
        BigInt vars = mod(baseTemp, two);
        

        if (compare(mod(baseTemp, two), zero) > 0) {
            return product(endResult, aTemp);
        } else {
            return endResult;
        }

    }

    public BigInt powerUtil(BigInt a, BigInt base, BigInt result) {

        Iterator itr1 = base.value.iterator();
        Long x = next(itr1);
        if ((x == 1)) {
            return a;
        }
        if ((x == 0)) {
            return new BigInt(1L);
        }

        base = divideby2(base, 2L);
        BigInt half = powerUtil(a, base, result);
        result = product(half, half);

        return result;
    }
    
    /**
     * 
     * @param n
     * @param d
     * @return 
     */
    public BigInt mod(BigInt n, BigInt d) {
        BigInt one = new BigInt(1L);
        BigInt zero = new BigInt("0");

        BigInt ret = new BigInt();
        if (compare(n, d) == -1) {
            return n;
        }
        BigInt low = new BigInt(1L);
        BigInt high = new BigInt(n.getValue());
        while (compare(high, low) >= 0) {
            BigInt mid = divideby2(add(low, high), 2L);
            BigInt prod = product(mid, d);
            trim(prod);
            int comp = compare(prod, n);
            if (comp == 0) {

                return zero;

            }
            if (comp == -1) {
                if (compare(product(subtract(mid, one), d), n) == 1) {
                    BigInt temp = product(subtract(mid, one), d);
                    return subtract(n, temp);
                }
                low = add(mid, one);
            }
            if (comp == 1) {
                if (compare(product(subtract(mid, one), d), n) == -1) {
                    BigInt temp = product(subtract(mid, one), d);
                    return subtract(n, temp);
                }
                high = subtract(mid, one);
            }
        }
        return ret;
    }
    
    /**
     * 
     * @param n
     * @return 
     */
    public BigInt squareRoot(BigInt n) {
        BigInt one = new BigInt(1L);
        BigInt zero = new BigInt("0");
        if (compare(n, zero) == 0) {
            return zero;
        }
        BigInt ret = new BigInt();
        BigInt low = new BigInt(1L);
        BigInt high = new BigInt(n.getValue());
        while (compare(high, low) >= 0) {
            BigInt mid = divideby2(add(low, high), 2L);
            BigInt prod = product(mid, mid);
            trim(prod);
            int comp = compare(prod, n);
            if (comp == 0) {
                trim(mid);
                return mid;
            }
            if (comp == -1) {
                if (compare(product(subtract(mid, one), subtract(mid, one)), n) == 1) {
                    return mid;
                }
                low = add(mid, one);
            }
            if (comp == 1) {
                if (compare(product(subtract(mid, one), subtract(mid, one)), n) == -1) {
                    return subtract(mid, one);
                }
                high = subtract(mid, one);
            }

        }
        return ret;
    }
    
    /**
     * 
     * @param n
     * @param d
     * @return 
     */
    public BigInt divide(BigInt n, BigInt d) {
        boolean sign_flag_x = false;
        boolean sign_flag_y = false;
        Long x = (Long) n.value.getLast();
        Long y = (Long) d.value.getLast();
        if (x < 0 && y > 0) {
            sign_flag_x = true;
        } else if (x > 0 && y < 0) {
            sign_flag_y = true;
        } else if (x < 0 && y < 0) {
            sign_flag_x = true;
            sign_flag_y = true;
        }
        if (sign_flag_x) {
            changeSign(n);

        }
        if (sign_flag_y) {

            changeSign(d);
        }

        BigInt zero = new BigInt("0");
        if (compare(n, d) == -1) {
            return zero;
        }
        if (compare(d, zero) == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        BigInt result = divideUtil(n, d);
        if (sign_flag_x ^ sign_flag_y) {
            changeSign(result);

        }
        return result;
    }

    private BigInt divideUtil(BigInt n, BigInt d) {
        BigInt one = new BigInt(1L);
        BigInt ret = new BigInt();
        BigInt low = new BigInt(1L);
        BigInt high = new BigInt(n.getValue());
        while (compare(high, low) >= 0) {
            BigInt mid = divideby2(add(low, high), 2L);

            BigInt prod = product(mid, d);
            trim(prod);
            int comp = compare(prod, n);
            if (comp == 0) {
                trim(mid);
                return mid;
            }
            if (comp == -1) {
                if (compare(product(subtract(mid, one), d), n) == 1) {
                    return mid;
                }
                low = add(mid, one);
            }
            if (comp == 1) {
                if (compare(product(subtract(mid, one), d), n) == -1) {
                    return subtract(mid, one);
                }
                high = subtract(mid, one);
            }
        }
        return ret;
    }
    
    /**
     * 
     * @param a
     * @param n
     * @return 
     */
    private BigInt divideby2(BigInt a, Long n) {
        BigInt temp1 = new BigInt(a.getValue());
        BigInt ans = new BigInt();
        boolean carry_flag = false;
        Collections.reverse(temp1.value);
        Iterator itr = temp1.value.iterator();
        Long x = next(itr);
        while (x != null) {
            if (carry_flag == true) {
                x = 10 + x;
            }
            if (x == 0) {
                ans.value.add(0L);
                carry_flag = false;
            } else if (x < 2) {
                carry_flag = true;
                ans.value.add(0L);

            } else {
                ans.value.add(x / n);
                if (x % 2 != 0) {
                    carry_flag = true;
                } else {
                    carry_flag = false;
                }
            }
            x = next(itr);
        }
        Collections.reverse(ans.value);
        trim(ans);
        return ans;
    }

    /**
     * Helper Method which iterate List and return next element
     *
     * @param itr : The iterator of the list
     * @return next element of the list or null if the next element is null
     */
    private static Long next(Iterator<Long> itr) {
        return itr.hasNext() ? itr.next() : null;
    }

    /**
     *
     */
    public void printList() {

        //Simple method to Print the values in the list
        System.out.print(B + ": ");
        for (Object val : value) {
            System.out.print(val + " ");
        }

        System.out.println("");
    }

    private void splitList(BigInt a, BigInt a1, BigInt a2) {
        Iterator slow = a.value.iterator();
        Iterator fast = a.value.iterator();

        while (next(fast) != null) {
            a1.value.add(next(slow));
            next(fast);

        }

        while (slow.hasNext()) {
            a2.value.add(next(slow));
        }

    }

    private void changeSign(BigInt number) {

        long value = -1 * (long) number.value.removeLast();
        number.value.addLast(value);

    }

}
