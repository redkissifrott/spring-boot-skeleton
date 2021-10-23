package com.nnk.springboot.domain;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class EqualTest {

	@Test
	void testCurve() {
		final CurvePoint curve = new CurvePoint(1, 10.3, 13.7);
		final CurvePoint curve2 = new CurvePoint(1, 10.3, 13.7);
		assertEquals(curve, curve2);
		assertEquals(curve.toString(), curve2.toString());
		assertEquals(curve.hashCode(), curve2.hashCode());
	}

	@Test
	void testBidList() {
		final BidList bidList = new BidList("Account Test", "Type Test", 10d);;
		final BidList bidList2 = new BidList("Account Test", "Type Test", 10d);;
		assertEquals(bidList, bidList2);
		assertEquals(bidList.toString(), bidList2.toString());
		assertEquals(bidList.hashCode(), bidList2.hashCode());
	}

	@Test
	void testRating() {
		final Rating rating = new Rating("Moodys Rating", "Sand PRating",
				"Fitch Rating", 10);
		final Rating rating2 = new Rating("Moodys Rating", "Sand PRating",
				"Fitch Rating", 10);
		assertEquals(rating, rating2);
		assertEquals(rating.toString(), rating2.toString());
		assertEquals(rating.hashCode(), rating2.hashCode());
	}

	@Test
	void testRuleName() {
		final RuleName ruleName = new RuleName("Rule Name", "Description",
				"Json", "Template", "SQL", "SQL Part");
		final RuleName ruleName2 = new RuleName("Rule Name", "Description",
				"Json", "Template", "SQL", "SQL Part");
		assertEquals(ruleName, ruleName2);
		assertEquals(ruleName.toString(), ruleName2.toString());
		assertEquals(ruleName.hashCode(), ruleName2.hashCode());
	}

	@Test
	void testTrade() {
		final Trade trade = new Trade("Trade Account", "Type", (double) 13);
		final Trade trade2 = new Trade("Trade Account", "Type", (double) 13);
		assertEquals(trade, trade2);
		assertEquals(trade.toString(), trade2.toString());
		assertEquals(trade.hashCode(), trade2.hashCode());
	}

}
