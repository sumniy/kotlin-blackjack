package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

class Blackjack(playerNames: List<String>) {
    private val cardDeck = CardDeck()
    val dealer: Dealer = Dealer(Hands(cardDeck.firstDraw()))
    val players: List<Player> = playerNames.map { Player(it, Hands(cardDeck.firstDraw())) }

    fun play() {
        players.forEach {
            while (it.hands.isNotFinished() && InputView.willReceiveCard(it.name)) {
                it.hit(cardDeck.draw())
                ResultView.printPlayerInfo(it)
            }
        }

        if (dealer.shouldHit()) {
            ResultView.printDealerHit()
            dealer.hit(cardDeck.draw())
        }
    }
}
