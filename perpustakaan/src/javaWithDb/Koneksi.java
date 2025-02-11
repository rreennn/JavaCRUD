package javaWithDb;

import java.sql.*;
import java.util.*;

public class Koneksi {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1/perpus";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        insert();
        show();
        update();
        show();
        delete();
        show();
    }

    public static void update() {
        Scanner inp = new Scanner(System.in);
        System.out.print("Masukkan ID Buku yang ingin di update : ");
        int id_buku = inp.nextInt();
        inp.nextLine();
        System.out.print("Masukkan Judul Buku baru : ");
        String judul_buku = inp.nextLine();
        System.out.print("Masukkan Id Penulis Buku baru : ");
        int penulis = inp.nextInt();
        System.out.print("Masukkan Stok Buku baru : ");
        int stok = inp.nextInt();
        System.out.print("Masukkan Tahun Buku baru : ");
        int tahun_terbit = inp.nextInt();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "UPDATE buku SET judul_buku = ?, penulis = ?, stok = ?, tahun_terbit = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, judul_buku);
            ps.setInt(2, penulis);
            ps.setInt(3, stok);
            ps.setInt(4, tahun_terbit);
            ps.setInt(5, id_buku);

            ps.execute();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete() {
        Scanner inp = new Scanner(System.in);
        System.out.print("Masukkan ID Buku yang ingin di hapus : ");
        int id_buku = inp.nextInt();
        String sql = "DELETE FROM buku WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_buku);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data berhasil dihapus");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM buku");
            int i = 1;
            while (rs.next()) {
                System.out.println("Data ke-" + i);
                System.out.println("Kode buku : " + rs.getInt("id"));
                System.out.println("Judul buku : " + rs.getString("judul_buku"));
                System.out.println("Penulis : " + rs.getInt("penulis"));
                System.out.println("Stok : " + rs.getInt("stok"));
                System.out.println("Tahun Terbit : " + rs.getInt("tahun_terbit"));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert() {
        Scanner inp = new Scanner(System.in);
        System.out.print("Masukkan ID Buku : ");
        int id_buku = inp.nextInt();
        inp.nextLine();
        System.out.print("Masukkan Judul Buku : ");
        String judul_buku = inp.nextLine();
        System.out.print("Masukkan Id Penulis Buku : ");
        int penulis = inp.nextInt();
        System.out.print("Masukkan Stok Buku : ");
        int stok = inp.nextInt();
        System.out.print("Masukkan Tahun Buku : ");
        int tahun_terbit = inp.nextInt();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "INSERT INTO buku (id, judul_buku, penulis, stok, tahun_terbit) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id_buku);
            ps.setString(2, judul_buku);
            ps.setInt(3, penulis);
            ps.setInt(4, stok);
            ps.setInt(5, tahun_terbit);

            ps.execute();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
