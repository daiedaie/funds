package com.mrbt.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TxtTrascant {
	public static void main(String[] args) {
		String file_path = "E:/mysql_log/mysql-bin.000004";
		BufferedReader buf;
		try {
			InputStream is = new FileInputStream(file_path);
			buf = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String abc;
			FileWriter writer = new FileWriter("E:/mysql_log/1.txt");
			while ((abc = buf.readLine()) != null) {
				writer.append(new String(abc.getBytes(), "utf-8"));
			}
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
