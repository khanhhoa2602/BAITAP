package thutap;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class main {
    private static Scanner sc;
	public static void main(String[] args) throws IOException, SQLException {
       sc = new Scanner(System.in);
       int n ,timeout;
       int c = 0;
        do {
            menu();
            System.out.println("Mời chọn chức năng:");
            c = sc.nextInt();
            switch (c) {
                case 1:
                	 System.out.println("Danh sach URL:");
                  ketnoi.danhsachurl();
                    break;
                case 2:
                	System.out.println("Chon so lan ping:");
                	n=sc.nextInt();
                	System.out.println("\nNhap timeout:");
                	timeout=sc.nextInt();
                  ketnoi.chonurl(n,timeout);
                 break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            sc.nextLine();
            System.out.println("\nNhan 1 so bat ky de tiep tuc-0");
            c = sc.nextInt();
        } while (c != 0);

    }
     private static void menu() {
       
        System.out.println("1.Danh sach URL");
        System.out.println("2.Chon URL");
        System.out.println("3.Thoát");
    }
}