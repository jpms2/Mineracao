package lucene;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
 
public class LuceneReadIndexFromFileExample 
{
    //directory contains the lucene indexes
    private static final String INDEX_DIR = "indexedFiles";
 
    public LuceneReturn makeQuery(String query) throws Exception 
    {
        //Create lucene searcher. It search over a single IndexReader.
        IndexSearcher searcher = createSearcher();
        ArrayList<String> pathList = new ArrayList<String>();
        //Search indexed contents using search term
        TopDocs foundDocs = searchInContent(query, searcher);
         
        //Total found documents
        System.out.println("Total Matches :: " + foundDocs.totalHits);
        System.out.println("Total Files :: " + foundDocs.scoreDocs.length);
        //Let's print out the path of files which have searched term
        for (ScoreDoc sd : foundDocs.scoreDocs) 
        {
            Document d = searcher.doc(sd.doc);
            pathList.add(d.get("path"));
        }
        LuceneReturn lr = new LuceneReturn(foundDocs.totalHits,foundDocs.scoreDocs.length,pathList);
        return lr;
    }
     
    private static TopDocs searchInContent(String textToFind, IndexSearcher searcher) throws Exception
    {
        //Esse analyzer é o que vai ter stopwords, stemming, etc
    	//esse tem filtro de stopwords, parece
        QueryParser qp = new QueryParser("contents", new StandardAnalyzer());
        Query query = qp.parse(textToFind);
         
        //search the index
        TopDocs hits = searcher.search(query, 204);
        return hits;
    }
 
    private static IndexSearcher createSearcher() throws IOException 
    {
        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
         
        //It is an interface for accessing a point-in-time view of a lucene index
        IndexReader reader = DirectoryReader.open(dir);
         
        //Index searcher
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }
}