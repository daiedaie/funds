package com.mrbt.utils;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

/**
 * 关闭lucene使用的实例
 * @author Administrator
 *
 */
public class LuceneCloseUtils {
	public static void generateClose(IndexWriter indexWriter,Directory directory,Analyzer analyzer){
		if(indexWriter != null)
			try {
				indexWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(directory != null)
			try {
				directory.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(analyzer != null)
			analyzer.close();
	}
	public static void searcherClose(IndexReader reader,Analyzer analyzer){
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(analyzer != null){
			analyzer.close();
		}
	}
	public static void closeAll(IndexReader reader,IndexWriter indexWriter,Directory directory,Analyzer analyzer){
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(indexWriter != null)
			try {
				indexWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(directory != null)
			try {
				directory.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(analyzer != null)
			analyzer.close();
	}
}
