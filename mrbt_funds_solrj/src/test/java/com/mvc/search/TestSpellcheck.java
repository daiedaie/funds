package com.mvc.search;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;

public class TestSpellcheck {
	public static void main(String[] args) throws IOException {
		HttpSolrClient solr = new HttpSolrClient("http://localhost:8080/solr/core");
		SolrQuery params = new SolrQuery();
		String token = "汇添富";
		params.set("qt", "/suggest");
		params.set("q", token);
		params.set("spellcheck", "on");
		params.set("spellcheck.build", "true");
		params.set("spellcheck.onlyMorePopular", "true");

		params.set("spellcheck.count", "100");
		params.set("spellcheck.alternativeTermCount", "4");
		params.set("spellcheck.onlyMorePopular", "true");

		params.set("spellcheck.extendedResults", "true");
		params.set("spellcheck.maxResultsForSuggest", "5");

		params.set("spellcheck.collate", "true");
		params.set("spellcheck.collateExtendedResults", "true");
		params.set("spellcheck.maxCollationTries", "5");
		params.set("spellcheck.maxCollations", "3");

		QueryResponse response = null;

		try {
			response = solr.query(params);
			System.out.println("查询耗时：" + response.getQTime());
		} catch (SolrServerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			solr.close();
		}

		SpellCheckResponse spellCheckResponse = response
				.getSpellCheckResponse();
		if (spellCheckResponse != null) {
			List<Suggestion> suggestionList = spellCheckResponse
					.getSuggestions();
			for (Suggestion suggestion : suggestionList) {
				System.out.println("Suggestions NumFound: "
						+ suggestion.getNumFound());
				System.out.println("Token: " + suggestion.getToken());
				System.out.print("Suggested: ");
				List<String> suggestedWordList = suggestion.getAlternatives();
				for (String word : suggestedWordList) {
					System.out.println(word + ", ");
				}
				System.out.println();
			}
			System.out.println();
			Map<String, Suggestion> suggestedMap = spellCheckResponse
					.getSuggestionMap();
			for (Map.Entry<String, Suggestion> entry : suggestedMap.entrySet()) {
				System.out.println("suggestionName: " + entry.getKey());
				Suggestion suggestion = entry.getValue();
				System.out.println("NumFound: " + suggestion.getNumFound());
				System.out.println("Token: " + suggestion.getToken());
				System.out.print("suggested: ");

				List<String> suggestedList = suggestion.getAlternatives();
				for (String suggestedWord : suggestedList) {
					System.out.print(suggestedWord + ", ");
				}
				System.out.println("\n\n");
			}

			Suggestion suggestion = spellCheckResponse.getSuggestion(token);
			System.out.println("NumFound: " + suggestion.getNumFound());
			System.out.println("Token: " + suggestion.getToken());
			System.out.print("suggested: ");
			List<String> suggestedList = suggestion.getAlternatives();
			for (String suggestedWord : suggestedList) {
				System.out.print(suggestedWord + ", ");
			}
			System.out.println("\n\n");

			System.out.println("The First suggested word for solr is : "
					+ spellCheckResponse.getFirstSuggestion(token));
			System.out.println("\n\n");

			List<Collation> collatedList = spellCheckResponse
					.getCollatedResults();
			if (collatedList != null) {
				for (Collation collation : collatedList) {
					System.out.println("collated query String: "
							+ collation.getCollationQueryString());
					System.out.println("collation Num: "
							+ collation.getNumberOfHits());
					List<Correction> correctionList = collation
							.getMisspellingsAndCorrections();
					for (Correction correction : correctionList) {
						System.out.println("original: "
								+ correction.getOriginal());
						System.out.println("correction: "
								+ correction.getCorrection());
					}
					System.out.println();
				}
			}
			System.out.println();
			System.out.println("The Collated word: "
					+ spellCheckResponse.getCollatedResult());
			System.out.println();
		}

		System.out.println("查询耗时：" + response.getQTime());

		solr.close();
	}
}