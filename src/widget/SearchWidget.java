package widget;
import java.io.File;
import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class SearchWidget {
	
	private static final int FIELD_LENGTH = 4;
	private Directory index_d;
	private Analyzer analyzer;
	private IndexSearcher searcher;
	private QueryParser q_parser;
	private Query query;
	private String[][] res;
	
	public SearchWidget() throws IOException {
		 index_d = MMapDirectory.open(new File("/index"));
		 analyzer = new IKAnalyzer();
		 searcher = new IndexSearcher(IndexReader.open(index_d));
	}
	
	public void search(String field,String query_string) throws ParseException {
//		System.out.println(field);
//		System.out.println(query_string);
		q_parser = new QueryParser(Version.LUCENE_35,field,analyzer);
		query = q_parser.parse(query_string);
	}
	
	public String[][] getResult() throws IOException {
		TopDocs hits = searcher.search(query,100);
		return getResult(hits.scoreDocs);
	}
	
	private String[][] getResult(ScoreDoc[] docs) throws CorruptIndexException, IOException {
		res = new String[docs.length][FIELD_LENGTH];
		for(int i = 0;i<docs.length;i++) {
			Document document = searcher.doc(docs[i].doc);
			res[i][0] = document.get("author");
			res[i][1] = document.get("title");
			res[i][2] = document.get("journal");
			res[i][3] = document.get("abstract");
		}
//		printResult();
		return res;
	} 
	
	@SuppressWarnings("unused")
	private void printResult() {
		for(int i=0;i<res.length;i++) {
			for(int j=0;j<res[0].length;j++) {
				System.out.println(res[i][j]);
			}
		}
	}
}
