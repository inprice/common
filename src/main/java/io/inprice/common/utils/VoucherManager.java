package io.inprice.common.utils;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * Generates and verifies totally random vouchers.
 *
 * @author mdpinar
 *
 */
public class VoucherManager {

  /*
   * Length is 33 Some characters like O, I, 0 are reserved.
   */
  private static final char[] baseNums = "831795264".toCharArray(); // all digits except for 0
  private static final char[] baseChars = "TFDSRXAVQJMNELZYCKUHGWPB".toCharArray(); // all chars except for O and I
  private static final char[] checksumSet = "U4KMDN8XJ5VPAG6RS1BT7C2Q9EZF3LYHW".toCharArray();

  private static final Random random = new Random();

  /**
   * Verifies whether given voucher is valid or not
   * 
   * @return boolean
   */
  public static boolean isValid(String voucher) {
    if (StringUtils.isBlank(voucher)) return false;

    int checkSum = 0;
    for (int i = 0; i < voucher.length() - 1; i++) {
      checkSum += voucher.charAt(i);
    }

    // the last char is the checksum
    return (voucher.charAt(voucher.length() - 1) == checksumSet[checkSum % checksumSet.length]);
  }

  /**
   * Generates random voucher code
   *
   * @return 8 chars voucher code
   */
  public static String generate() {
    int checkSum = 0;

    StringBuilder sb = new StringBuilder(8);

    // generating first six random chars
    for (int j = 0; j < 2; j++) {
      for (int i = 0; i < 2; i++) {
        char chr = findNextChar(sb, baseChars);
        sb.append(chr);
        checkSum += chr;
      }
      char chr = findNextChar(sb, baseNums);
      sb.append(chr);
      checkSum += chr;
    }

    // generating seventh random chars
    char chr = findNextChar(sb, checksumSet);
    sb.append(chr);
    checkSum += chr;

    // check sum char is appended
    sb.append(checksumSet[checkSum % checksumSet.length]);

    return sb.toString();
  }

  private static char findNextChar(StringBuilder sb, char[] charSet) {
    char chr = 0;
    do {
      chr = charSet[random.nextInt(charSet.length)];
    } while (sb.indexOf("" + chr) > -1);
    return chr;
  }

  /**
   * Tests
   * 
   */
  public static void main(String[] args) {
    System.err.println("Generated voucher:");
    //for (int i = 0; i < 5; i++) {
      System.err.println(generate());
    //}
  }

}
