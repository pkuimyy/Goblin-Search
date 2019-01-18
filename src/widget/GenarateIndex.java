package widget;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class GenarateIndex {
	public static void main(String[] args) throws IOException {
		Directory d_res = MMapDirectory.open(new File("./index"));
		
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_35,analyzer);
		GenarateIndex.createIndex(d_res,conf);
		analyzer.close();
		d_res.close();		
	}

	private static void createIndex(Directory d_res, IndexWriterConfig conf) throws CorruptIndexException, LockObtainFailedException, IOException {
		String[] data = new String[4];
		IndexWriter indexWriter = new IndexWriter(d_res,conf);
		File[] file_list = new File("./resource/cutted_file/en").listFiles();
		for(int i=0;i<file_list.length;i++) {
			data = readData(file_list[i]);
			Document doc = new Document();
			for(int j=0;j<4;j++) {
				String s = data[j];
				if(j==0) {
					Field author = new Field("author",s,Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					doc.add(author);
				}else if(j==1) {
					Field title = new Field("title",s,Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					doc.add(title);
				}else if(j==2) {
					Field journal = new Field("journal",s,Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					doc.add(journal);					
				}else if(j==3) {
					Field my_abstract = new Field("abstract",s,Field.Store.YES,Field.Index.ANALYZED,Field.TermVector.WITH_POSITIONS_OFFSETS);
					doc.add(my_abstract);
				}
			}
			indexWriter.addDocument(doc);
		}
		
		indexWriter.close();
		System.out.println("index created!");
	}

	private static String[] readData(File file) throws IOException {
		FileInputStream  in = new FileInputStream(file);
		byte[] tmp = new byte[(int) file.length()];
		in.read(tmp);
		in.close();
		String tmp_string = new String(tmp,"utf-8");
		if(tmp_string.trim().equals("")) {
			System.out.println(file.getName());
		}
		String[] data = tmp_string.split("\n");
		for(int i =0;i<data.length;i++) {
			data[i] = data[i].trim().substring(3);
		}
		return data;
	}
}
