
package bmsprojectcs2a;

import java.security.SecureRandom;

public class cardandpin {
    
    public  String generate_pin(){
    SecureRandom sr = new SecureRandom();
    int num = 1000 + (int)(sr.nextDouble() * 9000);
    return String.valueOf(num);
    }
    
    public  String generate_card(){
    SecureRandom sr = new SecureRandom();
    long num = 100000000000L + (long)(sr.nextDouble() * 900000000000L);
    return String.valueOf(num);
    }
    
}
