package com.winter.app.transfer.main;

import com.winter.app.transfer.Bus;
import com.winter.app.transfer.Subway;
import com.winter.app.transfer.card.Card;

public class TransferMain {

	public static void main(String[] args) {
		Subway subway = new Subway();
		Bus bus = new Bus();
		Card card = new Card();
		
		// 시작
		card.checkCard();
		bus.getBus();
		card.checkCard();
		
		card.checkCard();
		subway.getSubway();
		card.checkCard();
		
	}

}
