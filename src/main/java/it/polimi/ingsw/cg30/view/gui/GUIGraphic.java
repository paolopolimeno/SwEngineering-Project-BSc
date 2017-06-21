package it.polimi.ingsw.cg30.view.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Glow;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import it.polimi.ingsw.cg30.connection.client.Client;
import it.polimi.ingsw.cg30.connection.client.ClientNetworkInterface;
import it.polimi.ingsw.cg30.connection.client.Graphic;
import it.polimi.ingsw.cg30.model.Assistant;
import it.polimi.ingsw.cg30.model.Counselor;
import it.polimi.ingsw.cg30.model.PermissionCard;
import it.polimi.ingsw.cg30.model.PoliticCard;
import it.polimi.ingsw.cg30.model.actions.Action;
import it.polimi.ingsw.cg30.model.actions.AnotherMainAction;
import it.polimi.ingsw.cg30.model.actions.AssistantHiring;
import it.polimi.ingsw.cg30.model.actions.BuildEmporium;
import it.polimi.ingsw.cg30.model.actions.BuildEmporiumKing;
import it.polimi.ingsw.cg30.model.actions.BuyPermissionCard;
import it.polimi.ingsw.cg30.model.actions.ChangePermissionCards;
import it.polimi.ingsw.cg30.model.actions.CityTokenAction;
import it.polimi.ingsw.cg30.model.actions.ElectCounselor;
import it.polimi.ingsw.cg30.model.actions.ElectCounselorAssistant;
import it.polimi.ingsw.cg30.model.actions.FreePermissionCardAction;
import it.polimi.ingsw.cg30.model.actions.NoFastAction;
import it.polimi.ingsw.cg30.model.actions.RepeatPermissionCardAction;
import it.polimi.ingsw.cg30.model.actions.market.BuyAction;
import it.polimi.ingsw.cg30.model.actions.market.SellAction;
import it.polimi.ingsw.cg30.model.actions.market.Thing;
import it.polimi.ingsw.cg30.model.change.GameBoardChange;
import it.polimi.ingsw.cg30.model.change.PlayerChange;
import javafx.application.*;

public class GUIGraphic extends Application implements Graphic {

	private static final String ACTION_INFO = "Action INFO";
	private static final String PRICE = "PRICE: ";
	private static final String OWNER = "OWNER: ";
	private static final String ADD_TO_CART = "ADDED to CART!";
	private static final String BUY = "BUY";
	private static final String TIME_TO_SELL = "Time to Sell";
	private static final String INSERT_THE_PRICE = "Insert the Price";
	private static final String SELL = "SELL";
	
	private static final String YELLOW = "YELLOW";
	private static final String ORANGE = "ORANGE";
	private static final String BLUE = "BLUE";
	private static final String GREEN = "GREEN";
	private static final String BLACK = "BLACK";
	private static final String WHITE = "WHITE";
	private static final String PURPLE = "PURPLE";
	private static final String PINK = "PINK";
	private static final String JOLLY = "JOLLY";
	private static final String RED = "RED";

	private AnchorPane clear;
	
	// MAPPA
	private AnchorPane gameLayout;
	private ArrayList<CityLayout> cities;
	private ArrayList<Group> edges;
	private ArrayList<ImageView> seaBalcony;
	private ArrayList<ImageView> hillBalcony;
	private ArrayList<ImageView> mountainBalcony;
	private ArrayList<ImageView> kingBalcony;
	private ArrayList<ImageView> regions;
	private ArrayList<PermissionCardLayout> sea;
	private ArrayList<PermissionCardLayout> hill;
	private ArrayList<PermissionCardLayout> mountain;
	private Button firstInteractiveBonus;
	private Button secondInteractiveBonus;
	private Button thirdInteractiveBonus;

	// ACTION CARD
	private AnchorPane actionLayout;
	private ArrayList<ImageView> possibleActions;

	// CHAT
	private AnchorPane chat;
	private TextArea chatView;
	private TextField chatInput;

	// GAME SPACE
	private AnchorPane gameSpace;
	private ArrayList<ImageView> availableCounselors;

	// PLAYERVIEW
	private AnchorPane playerView;
	private Label assistantsNumber;
	private Label emporiumsNumber;
	private ArrayList<PermissionCardLayout> permissionCards;
	private ArrayList<ImageView> politicCards;
	private ArrayList<BonusCardLayout> bonusCards;
	private ImageView playerEmporiums;
	private ImageView emporiumRed = new ImageView("file:src/main/resources/images/red_emporium.png");
	private ImageView emporiumYellow = new ImageView("file:src/main/resources/images/yellow_emporium.png");
	private ImageView emporiumGreen = new ImageView("file:src/main/resources/images/green_emporium.png");
	private ImageView emporiumBlue = new ImageView("file:src/main/resources/images/blue_emporium.png");

	// OTHERPLAYER
	private AnchorPane otherPlayer;
	private Button otherPlayerButton;
	private ImageView playersEmporium;
	private ImageView assistant;
	private Label assistantsNumberPlayers;
	private Label emporiumsNumberPlayers;
	private ArrayList<BonusCardLayout> bonusCardsPlayer;
	ArrayList<PermissionCardLayout> permissionCardsPlayer;

	// MAIN THINGS
	private Stage primaryStage;
	private BorderPane border;
	private Client client;
	private int numberOfPlayers = 0;
	private Scene rootScene;
	private int roomSelection;
	private int mapSelection;
	private int numEmporium;
	private ArrayList<String> choice;
	private Action action;
	private int i;

	// MARKET
	private int numberOfAssistantsToSell = 0;
	private Thing thingTmp;
	private Action sellAction;

	// GAME LOGIC TO IMPROVE
	private Button sendActionButton;
	private Button endButton;
	private Button clearButton;

	// MAPPA
	private ImageView map = new ImageView("file:src/main/resources/images/map(ridotta).jpg");
	private ImageView assistants = new ImageView("file:src/main/resources/images/Assistant.png");

	// RE
	private ImageView king = new ImageView("file:src/main/resources/images/king.png");
	// CITTA
	private Image goldCity = new Image("file:src/main/resources/images/gold.png");
	private Image silverCity = new Image("file:src/main/resources/images/silver.png");
	private Image bronzeCity = new Image("file:src/main/resources/images/bronze.png");
	private Image ironCity = new Image("file:src/main/resources/images/metal.png");
	private Image purpleCity = new Image("file:src/main/resources/images/purple.png");
	// CARTE POLITICHE
	private Image blackCard = new Image("file:src/main/resources/images/black.png");
	private Image whiteCard = new Image("file:src/main/resources/images/white.png");
	private Image purpleCard = new Image("file:src/main/resources/images/purple_card.png");
	private Image pinkCard = new Image("file:src/main/resources/images/pink.png");
	private Image blueCard = new Image("file:src/main/resources/images/blue.png");
	private Image orangeCard = new Image("file:src/main/resources/images/orange.png");
	private Image jollyCard = new Image("file:src/main/resources/images/rainbowsmall.png");
	// CONSIGLIERI
	private Image blackCounselor = new Image("file:src/main/resources/images/consigliere_nero.png");
	private Image pinkCounselor = new Image("file:src/main/resources/images/consigliere_rosa.png");
	private Image orangeCounselor = new Image("file:src/main/resources/images/consigliere_arancione.png");
	private Image purpleCounselor = new Image("file:src/main/resources/images/consigliere_viola.png");
	private Image whiteCounselor = new Image("file:src/main/resources/images/consigliere_bianco.png");
	private Image blueCounselor = new Image("file:src/main/resources/images/consigliere_blu.png");
	// AZIONI
	private Image buildEmporium = new Image("file:src/main/resources/images/emporium.png");
	private Image buildEmporiumKing = new Image("file:src/main/resources/images/emporium_king.png");
	private Image buyPermissionCard = new Image("file:src/main/resources/images/take_permission_card.png");
	private Image electCounselor = new Image("file:src/main/resources/images/elect_counselor.png");
	private Image anotherMainAction = new Image("file:src/main/resources/images/another_main_action.png");
	private Image assistantHiring = new Image("file:src/main/resources/images/assistant_hiring.png");
	private Image electCounselorAssistant = new Image("file:src/main/resources/images/elect_counselor_assistant.png");
	private Image changePermissionCards = new Image("file:src/main/resources/images/change_permission_card.png");
	// AZIONI
	private ImageView actionCard = new ImageView("file:src/main/resources/images/action_card.png");
	// MAZZI
	private ImageView seaDeck = new ImageView("file:src/main/resources/images/sea.png");
	private ImageView hillsDeck = new ImageView("file:src/main/resources/images/hills.png");
	private ImageView mountainsDeck = new ImageView("file:src/main/resources/images/mountains.png");
	// BONUS
	private ImageView metalBonus = new ImageView("file:src/main/resources/images/metalTile.png");
	private ImageView seaBonus = new ImageView("file:src/main/resources/images/seaTile.png");
	private ImageView hillBonus = new ImageView("file:src/main/resources/images/hillsTile.png");
	private ImageView mountainBonus = new ImageView("file:src/main/resources/images/mountainsTile.png");
	private ImageView goldBonus = new ImageView("file:src/main/resources/images/goldTile.png");
	private ImageView silverBonus = new ImageView("file:src/main/resources/images/silverTile.png");
	private ImageView bronzeBonus = new ImageView("file:src/main/resources/images/bronzeTile.png");
	private ImageView kingBonus5 = new ImageView("file:src/main/resources/images/king5.png");
	private ImageView kingBonus4 = new ImageView("file:src/main/resources/images/king4.png");
	private ImageView kingBonus3 = new ImageView("file:src/main/resources/images/king3.png");
	private ImageView kingBonus2 = new ImageView("file:src/main/resources/images/king2.png");
	private ImageView kingBonus1 = new ImageView("file:src/main/resources/images/king1.png");
	private String css = GUIGraphic.class.getResource("style.css").toExternalForm();
	private ImageView mute = new ImageView("file:src/main/resources/images/Mute.png");

	// SOUNDS
	private URL resourceInitSong = getClass().getResource("deadmau5.mp3");
	private Media media = new Media(resourceInitSong.toString());
	private MediaPlayer song = new MediaPlayer(media);
	private URL resourceSample = getClass().getResource("sample.wav");
	private Media mediaSample = new Media(resourceSample.toString());
	private MediaPlayer sample = new MediaPlayer(mediaSample);

	private ComboBox<String> availableRooms;

	// SCENE
	private Scene initSetupScene;
	private Scene playersScene;
	private Scene buyScene;
	private Scene sellScene;
	private Scene priceScene;
	private Scene roomScene;

	/**
	 * this method starts the GUI thread and initializes all the main panes of
	 * the game
	 * 
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		choice = new ArrayList<>();
		this.client = Client.getInstance();
		this.client.setGraphic(this);

		this.primaryStage.setOnCloseRequest(e ->
			Client.getInstance().getConnection().disconnect()
		);

		gameLayout = new AnchorPane();
		actionLayout = new AnchorPane();
		playerView = new AnchorPane();
		otherPlayer = new AnchorPane();
		chat = new AnchorPane();
		gameSpace = new AnchorPane();
		possibleActions = new ArrayList<>();

		startGui();

	}

	/**
	 * this method shows a new stage with the first selection about the
	 * connection and the username
	 */
	private void startGui() {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Init Setup");
		window.setHeight(350);
		window.setWidth(350);

		Label label = new Label();
		label.setText("Insert Username!");

		TextField username = new TextField();
		username.setPromptText("username");
		username.setMaxWidth(200);

		Button socketButton = new Button("Connect Socket");
		socketButton.setPrefWidth(200);
		socketButton.setOnAction(e -> {

			window.close();
			client.connectGUI(username.getText(), "1");
			roomSelection(Client.getInstance().getUUID(), Client.getInstance().getConnection());

		});

		Button rmiButton = new Button("Connect RMI");
		rmiButton.setPrefWidth(200);
		rmiButton.setOnAction(e -> {

			window.close();
			client.connectGUI(username.getText(), "2");
			roomSelection(Client.getInstance().getUUID(), Client.getInstance().getConnection());

		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, username, socketButton, rmiButton);
		layout.setAlignment(Pos.CENTER);

		initSetupScene = new Scene(layout);
		window.setScene(initSetupScene);
		initSetupScene.getStylesheets().clear();
		initSetupScene.getStylesheets().add(css);
		window.show();

	}

	/**
	 * this method sets the root layout which is used to anchor the main panels
	 */
	public void initRootLayout() {

		border = new BorderPane();
		border.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
		border.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
		
		showChatView();
	}

	/**
	 * this method shows the chat starting after the room selection
	 */
	private void showChatView() {

		chat.getChildren().clear();
		chat.setLayoutX(border.getPrefWidth() * 0.66);
		chat.setLayoutY(0);

		if (!border.getChildren().contains(chat)) {
			border.getChildren().add(chat);
		}

		chat.setPrefHeight(border.getPrefHeight() * 0.21);
		chat.setPrefWidth(border.getPrefWidth() * 0.34);

		chatView = new TextArea();
		chatView.setLayoutX(0);
		chatView.setLayoutY(0);
		chatView.setPrefHeight(chat.getPrefHeight() * 0.95);
		chatView.setPrefWidth(chat.getPrefWidth());
		chatView.setEditable(false);
		chatView.setScrollTop(Double.MAX_VALUE);

		chat.getChildren().add(chatView);

		chatInput = new TextField();
		chatInput.setPromptText("chat here");
		chatInput.setLayoutY(chatView.getLayoutX() + chatView.getPrefHeight());
		chatInput.setLayoutX(0);
		chatInput.setPrefWidth(chat.getPrefWidth());
		chatInput.setPrefHeight(chat.getPrefHeight() * 0.05);
		chatInput.setOnAction(e -> {

			client.sendMessage(chatInput.getText());
			chatView.appendText("You: " + chatInput.getText() + "\n");

			chatInput.clear();
		});
		chat.getChildren().add(chatInput);
	}

	@Override
	public String selectPermissionCard() {
		return null;
	}

	@Override
	public String selectCity() {
		return null;
	}

	@Override
	public List<String> selectPoliticCards() {
		return null;
	}

	@Override
	public String selectBalcony() {
		return null;
	}

	@Override
	public String selectCounselor() {
		return null;
	}

	@Override
	public String selectBalconyFromAll() {
		return null;
	}

	@Override
	public String selectRegion() {
		return null;
	}

	@Override
	public ArrayList<String> insertThings() {
		return null;
	}

	@Override
	public String insertThing() {
		return null;
	}

	/**
	 * this method prints the game, it is called by the connection when receives
	 * a game board change
	 * 
	 * @param gameboard
	 */
	@Override
	public void printGameBoard(GameBoardChange gameBoard) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// SHOW MAP
				showGameBoardLayout();
				// GAME SPACE
				showGameSpace();
				// OTHER PLAYERS LAYOUT
				showOtherPlayersLayout();
			}
		});
	}

	/**
	 * this method shows an alert box
	 * 
	 * @param message
	 *            is the string showed
	 */
	public void alert(String message) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFO");
		alert.setHeaderText(message);
		alert.showAndWait();
	}

	/**
	 * this method prints all the things in the game board layout. It is
	 * launched by the printGameBoard method
	 */
	public void showGameBoardLayout() {

		if (song.getStatus() == Status.PLAYING) {
			
		} else {
			song.play();
			song.setVolume(1);
			song.setCycleCount(MediaPlayer.INDEFINITE);
		}

		if (numberOfPlayers == 0) {
			numberOfPlayers = client.getGameBoard().getPointsTrack().get(0).getDisks().size();
		}
		gameLayout.getChildren().clear();
		gameLayout.setLayoutX(0);
		gameLayout.setLayoutY(0);

		gameLayout.setPrefHeight(border.getPrefHeight() * 0.66);
		gameLayout.setPrefWidth(border.getPrefWidth() * 0.66);

		showWinner();
		addMap();
		addRegions();
		addCities();
		addBonusCards();
		addPermissionCardDecks();
		addCouncilors();
		addBonusButtons();
		addIndicatorDisks();
		addMute();

		if (!border.getChildren().contains(gameLayout)) {
			border.getChildren().add(gameLayout);
		}
	}

	/**
	 * this method initializes the mute button for the background song
	 */
	private void addMute() {

		mute.setDisable(false);
		mute.setLayoutX(border.getPrefWidth() * 0.95);
		mute.setLayoutY(border.getPrefHeight() * 0.9);
		mute.setFitHeight(border.getPrefHeight() * 0.03);
		mute.setFitWidth(border.getPrefWidth() * 0.03);

		mute.setOnMouseClicked(e -> {

			if (song.getVolume() == 1) {

				song.setVolume(0);
				mute.setEffect(new Glow(0.8));

			} else if (song.getVolume() == 0) {

				mute.setEffect(null);
				song.setVolume(1);
			}

		});

		if(!border.getChildren().contains(mute)){
			border.getChildren().add(mute);
		}

	}

	/**
	 * this method add to the game board the interactive bonuses as buttons
	 */
	private void addBonusButtons() {

		firstInteractiveBonus = new Button("1");

		firstInteractiveBonus.setLayoutX(gameLayout.getPrefWidth() * 0.18);
		firstInteractiveBonus.setLayoutY(gameLayout.getPrefHeight() * 0.95);

		firstInteractiveBonus.setPrefHeight(gameLayout.getPrefHeight() * 0.02);
		firstInteractiveBonus.setPrefWidth(gameLayout.getPrefWidth() * 0.005);

		firstInteractiveBonus.setOnAction(e -> {
			action = new CityTokenAction(choice.size(), choice);
			Client.getInstance().getConnection().sendAction(action);
		});

		secondInteractiveBonus = new Button("2");

		secondInteractiveBonus.setLayoutX(gameLayout.getPrefWidth() * 0.31);
		secondInteractiveBonus.setLayoutY(gameLayout.getPrefHeight() * 0.95);

		secondInteractiveBonus.setPrefHeight(gameLayout.getPrefHeight() * 0.02);
		secondInteractiveBonus.setPrefWidth(gameLayout.getPrefWidth() * 0.005);

		secondInteractiveBonus.setOnAction(e -> {
			action = new FreePermissionCardAction(choice.size() / 2, choice);
			Client.getInstance().getConnection().sendAction(action);
		});

		thirdInteractiveBonus = new Button("3");

		thirdInteractiveBonus.setLayoutX(gameLayout.getPrefWidth() * 0.505);
		thirdInteractiveBonus.setLayoutY(gameLayout.getPrefHeight() * 0.95);

		thirdInteractiveBonus.setPrefHeight(gameLayout.getPrefHeight() * 0.02);
		thirdInteractiveBonus.setPrefWidth(gameLayout.getPrefWidth() * 0.005);

		thirdInteractiveBonus.setOnAction(e -> {
			action = new RepeatPermissionCardAction(choice.size(), choice);
			Client.getInstance().getConnection().sendAction(action);
		});
		gameLayout.getChildren().addAll(firstInteractiveBonus, secondInteractiveBonus, thirdInteractiveBonus);
	}

	/**
	 * this method prints the player view and its possible actions. it is called
	 * by the connection when receives a player change
	 * 
	 * @param player
	 */
	@Override
	public void printPlayer(PlayerChange player) {

		Platform.runLater(new Runnable() {
	
			@Override
			public void run() {

				addClearButton(player);
				showPlayerView(player);
				showPossibleActions(player);
				
			}
		});
	}

	/**
	 * this method shows the politic cards, assistants, emporiums and permission
	 * cards of the player
	 * 
	 * @param player
	 */
	public void showPlayerView(PlayerChange player) {

		playerView.getChildren().clear();

		playerView.setLayoutX(border.getPrefWidth() * 0.01);
		playerView.setLayoutY(gameLayout.getLayoutY() + gameLayout.getPrefHeight() + border.getPrefWidth() * 0.06);

		if (!border.getChildren().contains(playerView)) {
			border.getChildren().add(playerView);
		}

		playerView.setPrefHeight(border.getPrefHeight() * 0.15);
		playerView.setPrefWidth(border.getPrefWidth() * 0.66);

		assistants.setLayoutX(playerView.getPrefWidth() * 0.02);
		assistants.setLayoutY(playerView.getPrefHeight() * 0.06);

		assistants.setFitHeight(playerView.getPrefHeight() * 0.35);
		assistants.setFitWidth(playerView.getPrefWidth() * 0.025);

		playerView.getChildren().add(assistants);

		assistantsNumber = new Label();

		assistantsNumber.setText(String.valueOf(player.getAssistants().size()));

		assistantsNumber.setLayoutX(assistants.getLayoutX());
		assistantsNumber.setLayoutY(assistants.getLayoutY() + assistants.getFitHeight());

		playerView.getChildren().add(assistantsNumber);

		if ((YELLOW).equalsIgnoreCase(player.getColor())) {

			playerEmporiums = new ImageView();
			playerEmporiums.setImage(emporiumYellow.getImage());
		}

		if ((BLUE).equalsIgnoreCase(player.getColor())) {

			playerEmporiums = new ImageView();
			playerEmporiums.setImage(emporiumBlue.getImage());
		}

		if ((GREEN).equalsIgnoreCase(player.getColor())) {

			playerEmporiums = new ImageView();
			playerEmporiums.setImage(emporiumGreen.getImage());
		}

		if ((RED).equalsIgnoreCase(player.getColor())) {

			playerEmporiums = new ImageView();
			playerEmporiums.setImage(emporiumRed.getImage());
		}

		playerEmporiums.setLayoutX(assistants.getLayoutX() + 2 * assistants.getFitWidth());
		playerEmporiums.setLayoutY(assistants.getLayoutY());

		playerEmporiums.setFitHeight(playerView.getPrefHeight() * 0.35);
		playerEmporiums.setFitWidth(playerView.getPrefWidth() * 0.06);

		playerView.getChildren().add(playerEmporiums);

		emporiumsNumber = new Label(String.valueOf(player.getEmporiums().size()));

		emporiumsNumber.setLayoutX(playerEmporiums.getLayoutX());
		emporiumsNumber.setLayoutY(playerEmporiums.getLayoutY() + playerEmporiums.getFitHeight());

		playerView.getChildren().add(emporiumsNumber);

		politicCards = new ArrayList<>();

		for (i = 0; i < player.getPoliticCards().size(); i++) {

			if ((BLACK).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(blackCard));
			}
			if ((WHITE).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(whiteCard));
			}
			if ((PURPLE).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(purpleCard));
			}
			if ((PINK).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(pinkCard));
			}
			if ((BLUE).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(blueCard));
			}
			if ((ORANGE).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(orangeCard));
			}
			if ((JOLLY).equalsIgnoreCase(player.getPoliticCards().get(i).getColor())) {
				politicCards.add(new ImageView(jollyCard));
			}
			if (i == 0) {
				politicCards.get(i).setLayoutX(assistants.getLayoutX());
			} else {
				politicCards.get(i)
						.setLayoutX(politicCards.get(i - 1).getLayoutX() + politicCards.get(i - 1).getFitWidth() + 5);
			}

			politicCards.get(i).setLayoutY(assistants.getLayoutY() + assistants.getFitHeight() * 2);

			politicCards.get(i).setFitHeight(playerView.getPrefHeight() * 0.4);
			politicCards.get(i).setFitWidth(playerView.getPrefWidth() * 0.04);

			playerView.getChildren().add(politicCards.get(i));
		}

		permissionCards = new ArrayList<>();

		for (i = 0; i < player.getPermissionCards().size(); i++) {

			permissionCards.add(new PermissionCardLayout(player.getPermissionCards().get(i), gameLayout));

			if (i == 0) {

				permissionCards.get(i).setLayoutX(playerEmporiums.getLayoutX() + playerEmporiums.getFitWidth() + 20);
			} else {

				permissionCards.get(i).setLayoutX(
						permissionCards.get(i - 1).getLayoutX() + permissionCards.get(i - 1).getPrefWidth() + 5);
			}
			permissionCards.get(i).setLayoutY(assistants.getLayoutY());

			playerView.getChildren().add(permissionCards.get(i));
		}

		bonusCards = new ArrayList<>();

		for (i = 0; i < player.getBonusCards().size(); i++) {

			bonusCards.add(new BonusCardLayout(player.getBonusCards().get(i).getNumberOfBonus(), gameLayout));

			if (i == 0) {

				bonusCards.get(i).setLayoutX(politicCards.get(politicCards.size() - 1).getLayoutX()
						+ politicCards.get(i).getFitWidth() + 20);
			} else {

				bonusCards.get(i)
						.setLayoutX(bonusCards.get(i - 1).getLayoutX() + bonusCards.get(i - 1).getPrefWidth() + 5);
			}

			bonusCards.get(i).setLayoutY(assistants.getLayoutY() + assistants.getFitHeight() * 2);

			playerView.getChildren().add(bonusCards.get(i));
		}
	}

	/**
	 * this method creates a new button which launches the display other players
	 * method
	 */
	private void showOtherPlayersLayout() {

		otherPlayer.getChildren().clear();

		otherPlayer.setLayoutX(border.getPrefWidth() * 0.66);
		otherPlayer.setLayoutY(gameSpace.getLayoutY() + gameSpace.getPrefHeight());

		otherPlayer.setPrefHeight(border.getPrefHeight() * 0.03);
		otherPlayer.setPrefWidth(border.getPrefWidth() * 0.34);

		otherPlayerButton = new Button("All the Players");

		otherPlayerButton.setLayoutX(0);
		otherPlayerButton.setLayoutY(0);

		otherPlayerButton.setPrefHeight(otherPlayer.getPrefHeight());
		otherPlayerButton.setPrefWidth(otherPlayer.getPrefWidth());

		otherPlayerButton.setOnAction(e -> displayOtherPlayers());

		otherPlayer.getChildren().add(otherPlayerButton);

		if (!border.getChildren().contains(otherPlayer)) {
			border.getChildren().add(otherPlayer);
		}
	}

	/**
	 * this method opens a new stage with the things of all the players
	 */
	private void displayOtherPlayers() {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Other Players");
		window.setHeight(400);
		window.setWidth(600);

		VBox layout = new VBox(10);

		for (i = 1; i < numberOfPlayers + 1; i++) {

			HBox playerContainer = new HBox(10);

			Label playerNumber = new Label(String.valueOf(i));
			playerContainer.getChildren().add(playerNumber);

			assistant = new ImageView();
			assistant.setImage(assistants.getImage());

			assistant.setFitHeight(playerView.getPrefHeight() * 0.35);
			assistant.setFitWidth(playerView.getPrefWidth() * 0.025);

			assistantsNumberPlayers = new Label();

			assistantsNumberPlayers.setText(String.valueOf(client.getGameBoard().getAssistantsNumber().get(i)));
			playerContainer.getChildren().addAll(assistant, assistantsNumberPlayers);

			if (("YELLOW").equalsIgnoreCase(client.getGameBoard().getPlayersColor().get(i))) {

				playersEmporium = emporiumYellow;
			}

			if (("BLUE").equalsIgnoreCase(client.getGameBoard().getPlayersColor().get(i))) {

				playersEmporium = emporiumBlue;
			}

			if (("GREEN").equalsIgnoreCase(client.getGameBoard().getPlayersColor().get(i))) {

				playersEmporium = emporiumGreen;
			}

			if (("RED").equalsIgnoreCase(client.getGameBoard().getPlayersColor().get(i))) {

				playersEmporium = emporiumRed;
			}

			playersEmporium.setFitHeight(playerView.getPrefHeight() * 0.35);
			playersEmporium.setFitWidth(playerView.getPrefWidth() * 0.06);

			emporiumsNumberPlayers = new Label(String.valueOf(client.getGameBoard().getEmporiumsNumber().get(i)));

			emporiumsNumberPlayers.setLayoutX(playerEmporiums.getLayoutX());
			emporiumsNumberPlayers.setLayoutY(playerEmporiums.getLayoutY() + playerEmporiums.getFitHeight());

			playerContainer.getChildren().addAll(playersEmporium, emporiumsNumberPlayers);

			permissionCardsPlayer = new ArrayList<>();

			for (int j = 0; j < client.getGameBoard().getPermissionCards().get(i).size(); j++) {

				permissionCardsPlayer.add(
						new PermissionCardLayout(client.getGameBoard().getPermissionCards().get(i).get(j), border));

				if (j == 0) {

					permissionCardsPlayer.get(j)
							.setLayoutX(playerEmporiums.getLayoutX() + playerEmporiums.getFitWidth() + 20);
				} else {

					permissionCardsPlayer.get(j).setLayoutX(permissionCardsPlayer.get(j - 1).getLayoutX()
							+ permissionCardsPlayer.get(j - 1).getPrefWidth() + 5);
				}

				permissionCardsPlayer.get(j).setPrefHeight(border.getPrefHeight() * 0.05);
				permissionCardsPlayer.get(j).setPrefWidth(border.getPrefWidth() * 0.05);

				playerContainer.getChildren().add(permissionCardsPlayer.get(j));
			}

			bonusCardsPlayer = new ArrayList<>();

			for (int j = 0; j < client.getGameBoard().getPlayersBonusCards().get(i).size(); j++) {

				bonusCardsPlayer.add(new BonusCardLayout(
						client.getGameBoard().getPlayersBonusCards().get(i).get(j).getNumberOfBonus(), border));

				if (j == 0) {

					bonusCardsPlayer.get(j)
							.setLayoutX(permissionCardsPlayer.get(permissionCardsPlayer.size() - 1).getLayoutX()
									+ permissionCardsPlayer.get(j).getPrefWidth() + 20);
				} else {

					bonusCardsPlayer.get(j).setLayoutX(
							bonusCardsPlayer.get(j - 1).getLayoutX() + bonusCardsPlayer.get(j - 1).getPrefWidth() + 5);
				}

				bonusCardsPlayer.get(j).setLayoutY(assistants.getLayoutY());
				bonusCardsPlayer.get(j).setPrefHeight(border.getPrefHeight() * 0.05);
				bonusCardsPlayer.get(j).setPrefWidth(border.getPrefWidth() * 0.05);

				playerContainer.getChildren().add(bonusCardsPlayer.get(j));
			}

			layout.getChildren().add(playerContainer);
		}

		layout.setAlignment(Pos.CENTER);

		playersScene = new Scene(layout);
		playersScene.getStylesheets().clear();
		playersScene.getStylesheets().add(css);
		window.setScene(playersScene);
		window.show();

	}

	/**
	 * this method shows the game space which contains the available counselors
	 */
	public void showGameSpace() {

		gameSpace.getChildren().clear();

		gameSpace.setLayoutX(border.getPrefWidth() * 0.66);
		gameSpace.setLayoutY(chat.getLayoutY() + chat.getPrefHeight() + border.getPrefWidth() * 0.01);

		if (!border.getChildren().contains(gameSpace)) {
			border.getChildren().add(gameSpace);
		}

		gameSpace.setPrefHeight(border.getPrefHeight() * 0.1);
		gameSpace.setPrefWidth(border.getPrefWidth() * 0.34);

		availableCounselors = new ArrayList<>();
		balconyFiller(client.getGameBoard().getCounselors(), availableCounselors);

		for (i = 0; i < availableCounselors.size(); i++) {

			availableCounselors.get(i).setPreserveRatio(true);
			availableCounselors.get(i).setFitWidth(border.getPrefWidth() * 0.02);

			if (i == 0) {
				availableCounselors.get(i).setLayoutX(border.getPrefWidth() * 0.01);
			} else {
				availableCounselors.get(i).setLayoutX(
						availableCounselors.get(i - 1).getFitWidth() + availableCounselors.get(i - 1).getLayoutX());
			}
			availableCounselors.get(i).setLayoutY(gameSpace.getPrefHeight() * 0.2);
			gameSpace.getChildren().add(availableCounselors.get(i));
		}
	}

	@Override
	public void printPossibleAction(PlayerChange player) {
	}

	/**
	 * this method shows the game logic which contains buttons and the possible
	 * actions of the player
	 * 
	 * @param player
	 */
	public void showPossibleActions(PlayerChange player) {

		actionLayout.getChildren().clear();

		actionLayout.setLayoutX(border.getPrefWidth() * 0.66);
		actionLayout.setLayoutY(otherPlayer.getLayoutY() + otherPlayer.getPrefHeight() + border.getPrefWidth() * 0.005);

		if (!border.getChildren().contains(actionLayout)) {
			border.getChildren().add(actionLayout);
		}

		actionLayout.setPrefHeight(border.getPrefHeight() * 0.4);
		actionLayout.setPrefWidth(border.getPrefWidth() * 0.34);		

		sendActionButton = new Button("Send Action!");

		sendActionButton.setLayoutX(0);
		sendActionButton.setLayoutY(0);

		sendActionButton.setPrefWidth(actionLayout.getPrefWidth() * 0.5);
		sendActionButton.setPrefHeight(actionLayout.getPrefHeight() * 0.05);

		actionLayout.getChildren().add(sendActionButton);

		sendActionButton.setOnMouseClicked(e -> sendActionButton.setEffect(new Glow(1)));

		sendActionButton.setOnAction(e -> {

			sample.play();
			endButton.setDisable(true);

			if (action instanceof ElectCounselor) {
				action = new ElectCounselor(choice);
			}
			if (action instanceof BuildEmporium) {
				action = new BuildEmporium(choice);
			}
			if (action instanceof BuildEmporiumKing) {
				action = new BuildEmporiumKing(choice);
			}
			if (action instanceof BuyPermissionCard) {
				action = new BuyPermissionCard(choice);
			}
			if (action instanceof ChangePermissionCards) {
				action = new ChangePermissionCards(choice);
			}
			if (action instanceof ElectCounselorAssistant) {
				action = new ElectCounselorAssistant(choice);
			}
			Client.getInstance().getConnection().sendAction(action);
			choice = new ArrayList<>();
			sample.stop();
		});

		endButton = new Button("End Choice");

		endButton.setLayoutX(
				sendActionButton.getLayoutX() + sendActionButton.getPrefWidth() + actionLayout.getPrefWidth() * 0.005);
		endButton.setLayoutY(0);

		endButton.setPrefWidth(actionLayout.getPrefWidth() * 0.5);
		endButton.setPrefHeight(actionLayout.getPrefHeight() * 0.05);

		actionLayout.getChildren().add(endButton);

		endButton.setOnMouseClicked(e -> endButton.setEffect(new Glow(1)));

		endButton.setOnAction(e -> {
			sample.play();

			if (choice.size() < 4) {
				endButton.setEffect(new Glow(1));
				choice.add("END");
				sample.stop();
			}
		});

		actionCard = new ImageView("file:src/main/resources/images/action_card.png");

		actionCard.setFitHeight(actionLayout.getPrefHeight() * 0.78);
		actionCard.setFitWidth(actionLayout.getPrefWidth());
		actionCard.setLayoutX(0);
		actionCard.setLayoutY(
				sendActionButton.getLayoutY() + sendActionButton.getPrefHeight() + border.getPrefWidth() * 0.01);

		actionLayout.getChildren().add(actionCard);

		endButton.setDisable(true);
		sendActionButton.setDisable(true);
		clearButton.setDisable(true);
		clearButton.setEffect(null);
		firstInteractiveBonus.setDisable(true);
		secondInteractiveBonus.setDisable(true);
		thirdInteractiveBonus.setDisable(true);

		possibleActions.clear();

		possibleActions.add(new ImageView(buildEmporium));
		actionPositioner(possibleActions.get(0), 0, 0.26);

		if (!actionLayout.getChildren().contains(possibleActions.get(0))) {
			actionLayout.getChildren().add(possibleActions.get(0));
		}
		possibleActions.add(new ImageView(buildEmporiumKing));
		actionPositioner(possibleActions.get(1), 0, 0.41);

		if (!actionLayout.getChildren().contains(possibleActions.get(1))) {
			actionLayout.getChildren().add(possibleActions.get(1));
		}
		possibleActions.add(new ImageView(buyPermissionCard));
		actionPositioner(possibleActions.get(2), 0, 0.56);

		if (!actionLayout.getChildren().contains(possibleActions.get(2))) {
			actionLayout.getChildren().add(possibleActions.get(2));
		}
		possibleActions.add(new ImageView(electCounselor));
		actionPositioner(possibleActions.get(3), 0, 0.71);

		if (!actionLayout.getChildren().contains(possibleActions.get(3))) {
			actionLayout.getChildren().add(possibleActions.get(3));
		}
		possibleActions.add(new ImageView(anotherMainAction));
		actionPositioner(possibleActions.get(4), 0.5, 0.26);

		if (!actionLayout.getChildren().contains(possibleActions.get(4))) {
			actionLayout.getChildren().add(possibleActions.get(4));
		}
		possibleActions.add(new ImageView(assistantHiring));
		actionPositioner(possibleActions.get(5), 0.5, 0.41);

		if (!actionLayout.getChildren().contains(possibleActions.get(5))) {
			actionLayout.getChildren().add(possibleActions.get(5));
		}
		possibleActions.add(new ImageView(electCounselorAssistant));
		actionPositioner(possibleActions.get(6), 0.5, 0.56);

		if (!actionLayout.getChildren().contains(possibleActions.get(6))) {
			actionLayout.getChildren().add(possibleActions.get(6));
		}
		possibleActions.add(new ImageView(changePermissionCards));
		actionPositioner(possibleActions.get(7), 0.5, 0.71);

		if (!actionLayout.getChildren().contains(possibleActions.get(7))) {
			actionLayout.getChildren().add(possibleActions.get(7));
		}
		Button noFastAction = new Button("Skip Fast Action!");

		noFastAction.setPrefHeight(actionLayout.getPrefHeight() * 0.05);
		noFastAction.setPrefWidth(actionLayout.getPrefWidth());

		noFastAction.setLayoutX(0);
		noFastAction.setLayoutY(actionCard.getLayoutY() + actionCard.getFitHeight());
		noFastAction.setDisable(true);

		if (!actionLayout.getChildren().contains(noFastAction)) {
			actionLayout.getChildren().add(noFastAction);
		}

		for (int i = 0; i < player.getPossibleActions().size(); i++) {

			if (player.getPossibleActions().get(i) instanceof CityTokenAction) {

				for (int j = 0; j < possibleActions.size(); j++) {
					possibleActions.get(j).setDisable(true);
				}
				choice = new ArrayList<>();
				cityTokenHelp();
				clearButton.setDisable(false);
				firstInteractiveBonus.setDisable(false);
			}

			if (player.getPossibleActions().get(i) instanceof FreePermissionCardAction) {

				for (int j = 0; j < possibleActions.size(); j++) {
					possibleActions.get(i).setDisable(true);
				}
				choice = new ArrayList<>();
				freePermissionCardHelp();
				clearButton.setDisable(false);
				secondInteractiveBonus.setDisable(false);
			}

			if (player.getPossibleActions().get(i) instanceof RepeatPermissionCardAction) {

				for (int j = 0; j < possibleActions.size(); j++) {
					possibleActions.get(i).setDisable(true);
				}
				choice = new ArrayList<>();
				repeatPermissionCardHelp();
				clearButton.setDisable(false);
				thirdInteractiveBonus.setDisable(false);
			}

			if (player.getPossibleActions().get(i) instanceof BuildEmporium) {

				possibleActions.get(0).setEffect(new Glow(0.8));
				possibleActions.get(0).setDisable(false);
				possibleActions.get(0).setOnMouseClicked(e -> {

					sample.play();
					choice = new ArrayList<>();

					buildEmporiumHelp();

					action = new BuildEmporium(null);

					possibleActions.get(0).setEffect(new Glow(1));
					sendActionButton.setDisable(false);
					endButton.setDisable(false);
					clearButton.setDisable(false);
					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}

					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof BuildEmporiumKing) {

				possibleActions.get(1).setEffect(new Glow(0.8));
				possibleActions.get(1).setDisable(false);
				possibleActions.get(1).setOnMouseClicked(e -> {

					sample.play();
					choice = new ArrayList<>();

					buildEmporiumKingHelp();

					action = new BuildEmporiumKing(null);

					possibleActions.get(1).setEffect(new Glow(1));
					sendActionButton.setDisable(false);
					endButton.setDisable(false);
					clearButton.setDisable(false);
					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof BuyPermissionCard) {
				possibleActions.get(2).setEffect(new Glow(0.8));
				possibleActions.get(2).setDisable(false);
				possibleActions.get(2).setOnMouseClicked(e -> {

					sample.play();
					choice = new ArrayList<>();

					buyPermissioncardHelp();

					action = new BuyPermissionCard(null);

					possibleActions.get(2).setEffect(new Glow(1));
					sendActionButton.setDisable(false);
					endButton.setDisable(false);
					clearButton.setDisable(false);
					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof ElectCounselor) {
				possibleActions.get(3).setEffect(new Glow(0.8));
				possibleActions.get(3).setDisable(false);
				possibleActions.get(3).setOnMouseClicked(e -> {

					sample.play();
					choice = new ArrayList<>();

					electCounselorHelp();

					action = new ElectCounselor(null);
					possibleActions.get(3).setEffect(new Glow(1));
					sendActionButton.setDisable(false);
					endButton.setDisable(false);
					clearButton.setDisable(false);
					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof AnotherMainAction) {
				possibleActions.get(4).setEffect(new Glow(0.8));
				possibleActions.get(4).setDisable(false);
				possibleActions.get(4).setOnMouseClicked(e -> {

					sample.play();
					action = new AnotherMainAction();
					clearButton.setDisable(false);
					Client.getInstance().getConnection().sendAction(action);

					possibleActions.get(4).setEffect(new Glow(1));

					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof AssistantHiring) {
				possibleActions.get(5).setEffect(new Glow(0.8));
				possibleActions.get(5).setDisable(false);
				possibleActions.get(5).setOnMouseClicked(e -> {

					sample.play();
					action = new AssistantHiring();
					clearButton.setDisable(false);
					Client.getInstance().getConnection().sendAction(action);

					possibleActions.get(5).setEffect(new Glow(1));

					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof ElectCounselorAssistant) {
				possibleActions.get(6).setEffect(new Glow(0.8));
				possibleActions.get(6).setDisable(false);
				possibleActions.get(6).setOnMouseClicked(e -> {

					sample.play();
					choice = new ArrayList<>();

					electCounselorHelp();

					action = new ElectCounselorAssistant(null);

					possibleActions.get(6).setEffect(new Glow(1));

					sendActionButton.setDisable(false);
					endButton.setDisable(false);
					clearButton.setDisable(false);

					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof ChangePermissionCards) {
				possibleActions.get(7).setEffect(new Glow(0.8));
				possibleActions.get(7).setDisable(false);
				possibleActions.get(7).setOnMouseClicked(e -> {

					sample.play();
					choice = new ArrayList<>();

					changePermissionCardsHelp();

					action = new ChangePermissionCards(null);

					possibleActions.get(7).setEffect(new Glow(0.8));
					sendActionButton.setDisable(false);
					endButton.setDisable(false);
					clearButton.setDisable(false);

					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof NoFastAction) {

				noFastAction.setEffect(new Glow(0.8));
				noFastAction.setDisable(false);
				noFastAction.setOnMouseClicked(e -> {

					sample.play();
					action = new NoFastAction();
					Client.getInstance().getConnection().sendAction(action);

					for (int j = 0; j < possibleActions.size(); j++) {
						possibleActions.get(j).setDisable(true);
					}
					sample.stop();
				});
			}
			if (player.getPossibleActions().get(i) instanceof SellAction) {

				sellAction = new SellAction();
				sellItems(sellAction, player);
			}
			if (player.getPossibleActions().get(i) instanceof BuyAction) {

				action = new BuyAction();
				buyItems(client.getGameBoard().getAllThingsForSale(), action);
			}
		}
	}

	/**
	 * this method helps with the interactive bonus repeat permission card
	 */
	private void repeatPermissionCardHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);
		alert.setHeaderText("REPEAT PERMISSION CARD BONUS");
		alert.setContentText("Select a Permission Card! - SEND ACTION - to end");

		selectPermissionCardAlert();

		alert.showAndWait();
	}

	/**
	 * this method enables the player permission cards for the player selection
	 */
	private void selectPermissionCardAlert() {

		for (i = 0; i < permissionCards.size(); i++) {

			permissionCards.get(i).setEffect(new Glow(0.7));
			permissionCards.get(i).setOnMouseClicked(e -> {

				PermissionCardLayout imgTmp = (PermissionCardLayout) e.getSource();
				imgTmp.setEffect(new Glow(0.7));
				int tmp = permissionCards.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));

				for (int j = 0; j < permissionCards.size(); j++) {

					permissionCards.get(j).setEffect(null);
				}
			});
		}
	}
	/**
	 * this method helps with the interactive bonus free permission card
	 */
	private void freePermissionCardHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);

		alert.setHeaderText("FREE PERMISSION CARD BONUS");
		alert.setContentText("Select a Region and a Permission Card! - SEND ACTION - to end");

		selectRegionAlert();
		selectPermissionCardRegionAlert();

		alert.showAndWait();
	}
	/**
	 * this method enables the showed permission cards for the player selection
	 */
	private void selectPermissionCardRegionAlert() {

		for (i = 0; i < 2; i++) {

			sea.get(i).setEffect(new Glow(1));
			sea.get(i).setOnMouseClicked(e -> {

				PermissionCardLayout imgTmp = (PermissionCardLayout) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = sea.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));
			});
		}

		for (i = 0; i < 2; i++) {

			hill.get(i).setEffect(new Glow(0.7));
			hill.get(i).setOnMouseClicked(e -> {

				PermissionCardLayout imgTmp = (PermissionCardLayout) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = hill.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));
			});
		}

		for (i = 0; i < 2; i++) {

			mountain.get(i).setEffect(new Glow(0.7));
			mountain.get(i).setOnMouseClicked(e -> {

				PermissionCardLayout imgTmp = (PermissionCardLayout) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = mountain.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));
			});
		}
	}

	/**
	 * this method helps with the interactive bonus city token
	 */
	private void cityTokenHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);

		alert.setHeaderText("CITY TOKEN BONUS");
		alert.setContentText("Select a City! - SEND ACTION - to end");

		selectCityAlert();

		alert.showAndWait();
	}
	/**
	 * this method enables the cities of the map for the player selection
	 */
	private void selectCityAlert() {

		for (i = 0; i < cities.size(); i++) {

			cities.get(i).setEffect(new Glow(0.7));
			cities.get(i).setOnMouseClicked(e -> {

				CityLayout imgTmp = (CityLayout) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = cities.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));

				for (int j = 0; j < cities.size(); j++) {

					cities.get(j).setEffect(null);
				}
			});
		}
	}
	/**
	 * this method enables the regions of the map for the player selection
	 */
	private void selectRegionAlert() {

		for (i = 0; i < regions.size(); i++) {

			regions.get(i).setEffect(new Glow(0.7));
			regions.get(i).setOnMouseClicked(e -> {

				ImageView imgTmp = (ImageView) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = regions.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));

				for (int j = 0; j < regions.size(); j++) {

					regions.get(j).setEffect(null);
				}
			});
		}
	}
	/**
	 * this method helps with the action change permission cards
	 */
	private void changePermissionCardsHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);

		alert.setHeaderText("CHANGE PERMISSION CARDS");
		alert.setContentText("Select a Balcony! - SEND ACTION - to end");

		selectRegionAlert();

		alert.showAndWait();
	}
	/**
	 * this method helps with the action elect counselor
	 */
	private void electCounselorHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);

		alert.setHeaderText("ELECT COUNSELOR!");
		alert.setContentText("Select a Counselor and a Balcony! - SEND ACTION - to end");

		selectCounselorAlert();
		selectRegionAlert();

		alert.showAndWait();
	}
	/**
	 * this method helps with the action select counselor
	 */
	private void selectCounselorAlert() {

		for (i = 0; i < availableCounselors.size(); i++) {

			availableCounselors.get(i).setEffect(new Glow(0.7));
			availableCounselors.get(i).setOnMouseClicked(e -> {

				ImageView imgTmp = (ImageView) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = availableCounselors.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));

				for (int j = 0; j < availableCounselors.size(); j++) {

					availableCounselors.get(j).setEffect(null);
				}
			});
		}
	}
	/**
	 * this method helps with the action buy permission card
	 */
	private void buyPermissioncardHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);

		alert.setHeaderText("BUY PERMISSION CARD!");
		alert.setContentText(
				"Select Politic Cards [END], Select a Permission Card! - SEND ACTION - to end");

		selectPoliticCardsAlert();
		selectRegionAlert();
		selectPermissionCardRegionAlert();

		alert.showAndWait();
	}
	/**
	 * this method enables the politic cards for the player selection
	 */
	private void selectPoliticCardsAlert() {

		for (i = 0; i < politicCards.size(); i++) {

			politicCards.get(i).setEffect(new Glow(0.4));
			politicCards.get(i).setOnMouseClicked(e -> {

				ImageView imgTmp = (ImageView) e.getSource();
				imgTmp.setEffect(new Glow(1));
				int tmp = politicCards.indexOf(imgTmp);
				choice.add(String.valueOf(tmp + 1));
			});
		}
	}
	/**
	 * this method helps with the action build emporium king
	 */
	private void buildEmporiumKingHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);

		alert.setHeaderText("BUILD EMPORIUM KING!");
		alert.setContentText("Select Politic Cards [END], Select a City! - SEND ACTION - to end");

		selectPoliticCardsAlert();
		selectCityAlert();

		alert.showAndWait();
	}
	/**
	 * this method helps with the action build emporium
	 */
	private void buildEmporiumHelp() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ACTION_INFO);
		alert.setHeaderText("BUILD EMPORIUM");
		alert.setContentText("Select a Permission Card and a City! - SEND ACTION - to end");

		selectPermissionCardAlert();
		selectCityAlert();

		alert.showAndWait();
	}
	/**
	 * this method set the position of the action in the action layout
	 * 
	 * @param action
	 * @param d
	 * @param e
	 */
	private void actionPositioner(ImageView action, double d, double e) {
		action.setFitWidth(actionLayout.getPrefWidth() * 0.5);
		action.setFitHeight(actionLayout.getPrefHeight() * 0.15);
		action.setLayoutX(actionLayout.getPrefWidth() * d);
		action.setLayoutY(actionLayout.getPrefHeight() * e);
		action.setDisable(true);
	}
	/**
	 * this method shows a new stage with the things that player can buy
	 * 
	 * @param things
	 * @param action
	 *            (buy action)
	 */
	@Override
	public void buyItems(List<Thing> things, Action action) {

		ArrayList<VBox> singleThing = new ArrayList<>();
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Time to Buy");
		window.setHeight(300);
		window.setWidth(800);

		AnchorPane thingsContainer = new AnchorPane();
		thingsContainer.setPrefHeight(300);
		thingsContainer.setPrefWidth(600);

		for (i = 0; i < client.getGameBoard().getAllThingsForSale().size(); i++) {

			if (client.getGameBoard().getAllThingsForSale().get(i).getThing() instanceof PermissionCard) {
				
				singleThing.add(new VBox(5));

				PermissionCardLayout card = new PermissionCardLayout(
						(PermissionCard) client.getGameBoard().getAllThingsForSale().get(i).getThing(), border);

				Label price = new Label(
						PRICE + String.valueOf(client.getGameBoard().getAllThingsForSale().get(i).getPrice()));
				price.setLayoutY(card.getPrefHeight() +50 + 60);

				Label owner = new Label(
						OWNER + String.valueOf(client.getGameBoard().getAllThingsForSale().get(i).getOwnerNumber()));
				owner.setLayoutY(card.getPrefHeight() + 60 + 100);
				
				Button number = new Button();
				number.setId(String.valueOf(i));
				number.setText(BUY);
				
				number.setLayoutY(card.getPrefHeight() + 50 + 140);
				
				singleThing.get(i).getChildren().addAll(card, price, owner, number);

				number.setOnMouseClicked(e -> {

					((BuyAction) action)
							.addThingToBuy(things.get((Integer.parseInt(((Button) e.getSource()).getId()))));
					alert(ADD_TO_CART);
				});
			}

			if (client.getGameBoard().getAllThingsForSale().get(i).getThing() instanceof PoliticCard) {				

				singleThing.add(new VBox(5));
				ImageView card = null;

				if ((BLACK).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {

					card = new ImageView(blackCard);
				}
				if ((WHITE).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {

					card = new ImageView(whiteCard);
				}
				if ((PURPLE).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {

					card = new ImageView(purpleCard);

				}
				if ((PINK).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {

					card = new ImageView(pinkCard);
				}
				if ((BLUE).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {

					card = new ImageView(blueCard);
				}
				if ((ORANGE).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {
					card = new ImageView(orangeCard);
				}
				if ((JOLLY).equals(
						((PoliticCard) client.getGameBoard().getAllThingsForSale().get(i).getThing()).getColor())) {
					card = new ImageView(jollyCard);
				}

				Label price = new Label(
						PRICE + String.valueOf(client.getGameBoard().getAllThingsForSale().get(i).getPrice()));

				Label owner = new Label(
						OWNER + String.valueOf(client.getGameBoard().getAllThingsForSale().get(i).getOwnerNumber()));

				Button number = new Button();
				number.setId(String.valueOf(i));
				number.setText(BUY);

				singleThing.get(i).getChildren().addAll(card, price, owner, number);

				number.setOnMouseClicked(e -> {

					((BuyAction) action)
							.addThingToBuy(things.get((Integer.parseInt(((Button) e.getSource()).getId()))));
					alert(ADD_TO_CART);
				});
			}
			if (client.getGameBoard().getAllThingsForSale().get(i).getThing() instanceof Assistant) {

				singleThing.add(new VBox(5));

				ImageView card = new ImageView("file:src/main/resources/images/Assistant.png");
				card.setFitHeight(80);
				card.setPreserveRatio(true);

				Label price = new Label(
						PRICE + String.valueOf(client.getGameBoard().getAllThingsForSale().get(i).getPrice()));

				Label owner = new Label(
						OWNER + String.valueOf(client.getGameBoard().getAllThingsForSale().get(i).getOwnerNumber()));

				Button number = new Button();
				number.setId(String.valueOf(i));
				number.setText(BUY);

				singleThing.get(i).getChildren().addAll(card, price, owner, number);
				
				number.setOnMouseClicked(e -> {

					((BuyAction) action).addThingToBuy(things.get(Integer.parseInt(((Button) e.getSource()).getId())));
					alert(ADD_TO_CART);

				});
			}
			
			thingsContainer.getChildren().add(singleThing.get(i));
			
			singleThing.get(i).setAlignment(Pos.CENTER);
			singleThing.get(i).setLayoutY(50);
			
			singleThing.get(i).setPrefHeight(200);
			singleThing.get(i).setPrefWidth(100);
			
			if(i==0){
				
				singleThing.get(i).setLayoutX(100);
				
			} else {
				
				singleThing.get(i).setLayoutX(singleThing.get(i-1).getLayoutX() + singleThing.get(i).getPrefWidth()+20);
			}
	
		}
		
		Button endBuyButton = new Button("PAY");
		endBuyButton.setOnAction(e -> {

			client.getConnection().sendAction(action);
			window.close();

		});
		
		endBuyButton.setLayoutX(300);
		endBuyButton.setLayoutY(50);
		
		thingsContainer.getChildren().add(endBuyButton);
		buyScene = new Scene(thingsContainer);
		buyScene.getStylesheets().clear();
		buyScene.getStylesheets().add(css);
		window.setScene(buyScene);
		window.show();
	}
	/**
	 * this method shows the things that player can sell
	 * 
	 * @param action
	 *            (sell action)
	 */
	@Override
	public void sellItems(Action action){
	}
	/**
	 * this method shows the things that player can sell
	 * 
	 * @param action
	 *            (sell action)
	 */
	public void sellItems(Action action, PlayerChange player) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(TIME_TO_SELL);
		window.setHeight(300);
		window.setWidth(600);

		AnchorPane thingsCanSale = new AnchorPane();

		assistants.setLayoutY(120);
		assistants.setLayoutX(100);
		
		assistants.setFitHeight(playerView.getPrefHeight() * 0.35);
		assistants.setFitWidth(playerView.getPrefWidth() * 0.025);

		assistants.setOnMouseClicked(e -> {

			selectPriceAssistant(action, player);
		});

		thingsCanSale.getChildren().add(assistants);

		for (i = 0; i < player.getPoliticCards().size(); i++) {

			politicCards.get(i).setFitHeight(playerView.getPrefHeight() * 0.4);
			politicCards.get(i).setFitWidth(playerView.getPrefWidth() * 0.04);
			
			politicCards.get(i).setLayoutY(120);
			
			if (i == 0) {
				politicCards.get(i).setLayoutX(assistants.getLayoutX() + assistants.getFitWidth()+5);
			} else {
				politicCards.get(i)
						.setLayoutX(politicCards.get(i - 1).getLayoutX() + politicCards.get(i - 1).getFitWidth() + 5);
			}
			
			politicCards.get(i).setOnMouseClicked(e -> {

				ImageView imgTmp = (ImageView) e.getSource();
				int tmp = politicCards.indexOf(imgTmp);
				selectPricePoliticCard(action, tmp, player);

			});
		}

		thingsCanSale.getChildren().addAll(politicCards);

		for (i = 0; i < player.getPermissionCards().size(); i++) {

			permissionCards.get(i).setPrefHeight(border.getPrefHeight() * 0.065);
			permissionCards.get(i).setPrefWidth(border.getPrefWidth() * 0.065);
			
			permissionCards.get(i).setLayoutY(120);
			
			if (i == 0) {
				permissionCards.get(i).setLayoutX(politicCards.get(politicCards.size()-1).getLayoutX() + politicCards.get(politicCards.size()-1).getFitWidth() + 5);
			} else {
				permissionCards.get(i)
						.setLayoutX(permissionCards.get(i - 1).getLayoutX() + permissionCards.get(i - 1).getPrefWidth() + 5);
			}

			permissionCards.get(i).setOnMouseClicked(e -> {

				PermissionCardLayout imgTmp = (PermissionCardLayout) e.getSource();
				int tmp = permissionCards.indexOf(imgTmp);
				selectPricePermissionCard(action, tmp, player);

			});
		}

		thingsCanSale.getChildren().addAll(permissionCards);

		Button endSellButton = new Button(SELL);
		
		endSellButton.setLayoutY(50);
		endSellButton.setLayoutX(250);
		
		endSellButton.setOnAction(e -> {

			Client.getInstance().getConnection().sendAction(action);
			window.close();
		});

		thingsCanSale.getChildren().add(endSellButton);

		endSellButton.setAlignment(Pos.BOTTOM_CENTER);

		sellScene = new Scene(thingsCanSale);
		sellScene.getStylesheets().clear();
		sellScene.getStylesheets().add(css);
		window.setScene(sellScene);
		window.showAndWait();
	}

	/**
	 * this method opens a new stage for the selection of the price for a
	 * permission card
	 * 
	 * @param action
	 *            sell action
	 * @param i
	 *            index of the permission card
	 */
	private void selectPricePermissionCard(Action action, int i, PlayerChange player) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(TIME_TO_SELL);
		window.setHeight(200);
		window.setWidth(250);

		VBox defaultForm = new VBox(10);
		defaultForm.setAlignment(Pos.CENTER);

		Label label = new Label(INSERT_THE_PRICE);

		TextField defaultPrice = new TextField();
		defaultPrice.setPromptText("Price");
		defaultPrice.setMaxWidth(150);

		Button defaultButton = new Button(SELL);
		defaultButton.setOnAction(e -> {

			thingTmp = new Thing(Integer.parseInt(defaultPrice.getText()), player.getPermissionCards().get(i),
					Client.getInstance().getPlayer().getPlayerNumber());

			((SellAction) action).addThingforSale(thingTmp);
			window.close();
		});
		defaultButton.setMaxWidth(150);

		defaultForm.getChildren().addAll(label, defaultPrice, defaultButton);

		priceScene = new Scene(defaultForm);
		window.setScene(priceScene);
		window.showAndWait();
	}

	/**
	 * this method opens a new stage for the selection of the price for a
	 * permission card
	 * 
	 * @param action
	 *            sell action
	 * @param i
	 *            index of the politic card
	 */
	private void selectPricePoliticCard(Action action, int i, PlayerChange player) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(TIME_TO_SELL);
		window.setHeight(200);
		window.setWidth(250);

		VBox defaultForm = new VBox(10);
		defaultForm.setAlignment(Pos.CENTER);

		Label label = new Label(INSERT_THE_PRICE);

		TextField defaultPrice = new TextField();
		defaultPrice.setPromptText("price");
		defaultPrice.setMaxWidth(150);

		Button defaultButton = new Button(SELL);
		defaultButton.setOnAction(e -> {

			thingTmp = new Thing(Integer.parseInt(defaultPrice.getText()), player.getPoliticCards().get(i),
					Client.getInstance().getPlayer().getPlayerNumber());

			((SellAction) action).addThingforSale(thingTmp);
			window.close();
		});
		defaultButton.setPrefWidth(150);

		defaultForm.getChildren().addAll(label, defaultPrice, defaultButton);

		priceScene = new Scene(defaultForm);
		window.setScene(priceScene);
		window.showAndWait();
	}

	/**
	 * this method opens a new stage for the selection of the price and the
	 * number of assistants
	 * 
	 * @param action
	 *            sell action
	 */
	private void selectPriceAssistant(Action action, PlayerChange player) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Time to Sell");
		window.setHeight(200);
		window.setWidth(250);

		VBox assistantForm = new VBox(10);
		assistantForm.setAlignment(Pos.CENTER);

		Label label = new Label("Number of Assistants");

		ComboBox<String> availableAssistants = new ComboBox<>();
		availableAssistants.setPromptText("Available Assistants");
		availableAssistants.setPrefWidth(150);

		for (i = 0; i < player.getAssistants().size(); i++) {
			availableAssistants.getItems().add(String.valueOf(i + 1));
		}
		availableAssistants.setOnAction(
				e -> numberOfAssistantsToSell = availableAssistants.getSelectionModel().getSelectedIndex() + 1);

		Label label2 = new Label("Insert Price");

		TextField assistantPrice = new TextField();
		assistantPrice.setPromptText("Price of assistants");
		assistantPrice.setMaxWidth(150);

		Button sellButtonAssistants = new Button("Sell Assistants!");
		sellButtonAssistants.setOnAction(e -> {

			for (i = 0; i < numberOfAssistantsToSell; i++) {

				thingTmp = new Thing(Integer.parseInt(assistantPrice.getText()),
						Client.getInstance().getPlayer().getAssistants().get(i),
						Client.getInstance().getPlayer().getPlayerNumber());

				((SellAction) action).addThingforSale(thingTmp);
			}
			window.close();
		});
		sellButtonAssistants.setMaxWidth(150);
		assistantForm.getChildren().addAll(label, availableAssistants, label2, assistantPrice, sellButtonAssistants);

		priceScene = new Scene(assistantForm);
		window.setScene(priceScene);
		window.showAndWait();
	}

	/**
	 * this method sends a message in broadcast to all the players appending the
	 * string in the chat view
	 * 
	 * @param username
	 *            of the player
	 * @param message
	 *            wants to send
	 */
	@Override
	public void printChatMessage(String username, String message) {
		chatView.appendText(username + ": " + message + "\n");
	}

	@Override
	public void commandListener() {
	}

	/**
	 * this method open a new stage for the room selection, in which the player can join a room or create
	 * a new one
	 * @param uuid
	 * @param connection
	 */
	@Override
	public void roomSelection(UUID uuid, ClientNetworkInterface connection) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Room Selection");
		window.setHeight(500);
		window.setWidth(350);

		Label label = new Label();
		label.setText("Select a Room!");

		availableRooms = new ComboBox<>();
		availableRooms.setPromptText("Rooms");
		availableRooms.getItems().addAll(Client.getInstance().getRoomsList());
		availableRooms.setPrefWidth(200);

		availableRooms.setOnAction(e -> 
			roomSelection = availableRooms.getSelectionModel().getSelectedIndex()
		);

		Button joinButton = new Button("Join");
		joinButton.setPrefWidth(200);
		joinButton.setOnAction(e -> {

			connection.joinRoom(uuid, Client.getInstance().getRoomsList().get(roomSelection));
			window.close();
			initRootLayout();
			rootScene = new Scene(border);
			rootScene.getStylesheets().add(css);
			primaryStage.setTitle("Council Of Four - " + client.getUsername());
			primaryStage.setScene(rootScene);
			primaryStage.show();
		});

		Label labelCreate = new Label();
		labelCreate.setText("Create a Room!");

		TextField roomName = new TextField();
		roomName.setPromptText("name of the room");
		roomName.setMaxWidth(200);

		TextField playerNumber = new TextField();
		playerNumber.setPromptText("number of players");
		playerNumber.setMaxWidth(200);

		ComboBox<String> maps = new ComboBox<>();
		maps.setPromptText("Select the map");
		maps.getItems().addAll(client.getMapsList());
		maps.setPrefWidth(200);

		mapSelection = 1;

		maps.setOnAction(e -> {
			mapSelection = maps.getSelectionModel().getSelectedIndex();
		});
		
		
		ComboBox<String> emporiums = new ComboBox<>();
		emporiums.setPromptText("Emporiums");
		emporiums.getItems().addAll("10","9","8","7","6","5","4","3");
		emporiums.setPrefWidth(200);

		numEmporium = 10;

		emporiums.setOnAction(e -> {
			numEmporium = Integer.parseInt(emporiums.getSelectionModel().getSelectedItem());
		});

		Button createButton = new Button("Create");
		createButton.setPrefWidth(200);
		createButton.setOnAction(e -> {
			String mapName = Client.getInstance().getMapsList().get(mapSelection);
			connection.createRoom(uuid, roomName.getText(), mapName, Integer.parseInt(playerNumber.getText()), numEmporium);
			numberOfPlayers = Integer.parseInt(playerNumber.getText());

			window.close();
			initRootLayout();
			rootScene = new Scene(border);
			rootScene.getStylesheets().clear();
			rootScene.getStylesheets().add(css);
			primaryStage.setTitle("Council Of Four - " + client.getUsername());
			primaryStage.setScene(rootScene);
			primaryStage.show();

		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, availableRooms, joinButton, labelCreate, maps, roomName, playerNumber, emporiums,
				createButton);
		layout.setAlignment(Pos.CENTER);

		roomScene = new Scene(layout);
		roomScene.getStylesheets().clear();
		roomScene.getStylesheets().add(css);
		window.setScene(roomScene);

		window.showAndWait();
	}

	/**
	 * this method adds the decks of permission cards to the game board
	 */
	private void addPermissionCardDecks() {

		sea = new ArrayList<>();

		PermissionCardLayout seaFirst = new PermissionCardLayout(
				client.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(0), gameLayout);

		seaFirst.setLayoutX(seaDeck.getLayoutX() + seaDeck.getFitWidth() + gameLayout.getPrefWidth() * 0.01);
		seaFirst.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		gameLayout.getChildren().add(seaFirst);

		PermissionCardLayout seaSecond = new PermissionCardLayout(
				client.getGameBoard().getRegions().get(0).getShowedPermissionCards().get(1), gameLayout);

		seaSecond.setLayoutX(seaFirst.getLayoutX() + seaFirst.getPrefWidth() + gameLayout.getPrefWidth() * 0.01);
		seaSecond.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		gameLayout.getChildren().add(seaSecond);

		sea.add(seaFirst);
		sea.add(seaSecond);

		hill = new ArrayList<>();

		PermissionCardLayout hillFirst = new PermissionCardLayout(
				client.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(0), gameLayout);

		hillFirst.setLayoutX(hillsDeck.getLayoutX() + hillsDeck.getFitWidth() + gameLayout.getPrefWidth() * 0.01);
		hillFirst.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		gameLayout.getChildren().add(hillFirst);

		PermissionCardLayout hillSecond = new PermissionCardLayout(
				client.getGameBoard().getRegions().get(1).getShowedPermissionCards().get(1), gameLayout);

		hillSecond.setLayoutX(hillFirst.getLayoutX() + hillFirst.getPrefWidth() + gameLayout.getPrefWidth() * 0.01);
		hillSecond.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		gameLayout.getChildren().add(hillSecond);

		hill.add(hillFirst);
		hill.add(hillSecond);

		mountain = new ArrayList<>();

		PermissionCardLayout mountainFirst = new PermissionCardLayout(
				client.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(0), gameLayout);

		mountainFirst.setLayoutX(
				mountainsDeck.getLayoutX() + mountainsDeck.getFitWidth() + gameLayout.getPrefWidth() * 0.01);
		mountainFirst.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		gameLayout.getChildren().add(mountainFirst);

		PermissionCardLayout mountainSecond = new PermissionCardLayout(
				client.getGameBoard().getRegions().get(2).getShowedPermissionCards().get(1), gameLayout);

		mountainSecond.setLayoutX(
				mountainFirst.getLayoutX() + mountainFirst.getPrefWidth() + gameLayout.getPrefWidth() * 0.01);
		mountainSecond.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		gameLayout.getChildren().add(mountainSecond);

		mountain.add(mountainFirst);
		mountain.add(mountainSecond);
	}

	/**
	 * this method add the bonus cards to the game board
	 */
	private void addBonusCards() {

		
		if(client.getGameBoard().getRegions().get(0).getRegionBonus() !=null){

			seaBonus.setLayoutX(gameLayout.getPrefWidth() * 0.25);
			seaBonus.setLayoutY(gameLayout.getPrefHeight() * 0.6);

			seaBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			seaBonus.setPreserveRatio(true);

			gameLayout.getChildren().add(seaBonus);
		}
		
		if(client.getGameBoard().getRegions().get(1).getRegionBonus() !=null){


			hillBonus.setLayoutX(gameLayout.getPrefWidth() * 0.55);
			hillBonus.setLayoutY(gameLayout.getPrefHeight() * 0.6);
	
			hillBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			hillBonus.setPreserveRatio(true);
	
			gameLayout.getChildren().add(hillBonus);
			
		}
		
		if(client.getGameBoard().getRegions().get(2).getRegionBonus() !=null){

			mountainBonus.setLayoutX(gameLayout.getPrefWidth() * 0.88);
			mountainBonus.setLayoutY(gameLayout.getPrefHeight() * 0.6);
	
			mountainBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			mountainBonus.setPreserveRatio(true);
	
			gameLayout.getChildren().add(mountainBonus);
		
		}

		if(!client.getGameBoard().getKingBonusCards().isEmpty() &&
				client.getGameBoard().getKingBonusCards().get(0) != null){
		
			kingBonus5.setLayoutX(gameLayout.getPrefWidth() * 0.88);
			kingBonus5.setLayoutY(gameLayout.getPrefHeight() * 0.88);
			kingBonus5.setRotate(43);
	
			kingBonus5.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			kingBonus5.setPreserveRatio(true);
	
			gameLayout.getChildren().add(kingBonus5);
		
		}

		if(client.getGameBoard().getKingBonusCards().size() > 1 &&
				client.getGameBoard().getKingBonusCards().get(1) != null){
		
			kingBonus4.setLayoutX(gameLayout.getPrefWidth() * 0.88);
			kingBonus4.setLayoutY(gameLayout.getPrefHeight() * 0.88);
			kingBonus4.setRotate(43);
	
			kingBonus4.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			kingBonus4.setPreserveRatio(true);
	
			gameLayout.getChildren().add(kingBonus4);
		
		}
		
		if(client.getGameBoard().getKingBonusCards().size() > 2 &&
		   client.getGameBoard().getKingBonusCards().get(2) != null){

			kingBonus3.setLayoutX(gameLayout.getPrefWidth() * 0.88);
			kingBonus3.setLayoutY(gameLayout.getPrefHeight() * 0.88);
			kingBonus3.setRotate(43);
	
			kingBonus3.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			kingBonus3.setPreserveRatio(true);
	
			gameLayout.getChildren().add(kingBonus3);
			
		}
		
		if(client.getGameBoard().getKingBonusCards().size() > 3 &&
		   client.getGameBoard().getKingBonusCards().get(3) != null){

			kingBonus2.setLayoutX(gameLayout.getPrefWidth() * 0.88);
			kingBonus2.setLayoutY(gameLayout.getPrefHeight() * 0.88);
			kingBonus2.setRotate(43);
	
			kingBonus2.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			kingBonus2.setPreserveRatio(true);
	
			gameLayout.getChildren().add(kingBonus2);
		
		}

		if(client.getGameBoard().getKingBonusCards().size() > 4 &&
		   client.getGameBoard().getKingBonusCards().get(4) != null){
		
			kingBonus1.setLayoutX(gameLayout.getPrefWidth() * 0.88);
			kingBonus1.setLayoutY(gameLayout.getPrefHeight() * 0.88);
			kingBonus1.setRotate(43);
	
			kingBonus1.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			kingBonus1.setPreserveRatio(true);
	
			gameLayout.getChildren().add(kingBonus1);
		
		}
		
		if(client.getGameBoard().getGoldBonus()!= null){

			goldBonus.setLayoutX(gameLayout.getPrefWidth() * 0.89);
			goldBonus.setLayoutY(gameLayout.getPrefHeight() * 0.96);
			goldBonus.setRotate(43);
	
			goldBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			goldBonus.setPreserveRatio(true);
	
			gameLayout.getChildren().add(goldBonus);
		
		}
		
		if(client.getGameBoard().getSilverBonus() != null){

			silverBonus.setLayoutX(gameLayout.getPrefWidth() * 0.84);
			silverBonus.setLayoutY(gameLayout.getPrefHeight() * 0.97);
			silverBonus.setRotate(43);
	
			silverBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			silverBonus.setPreserveRatio(true);
	
			gameLayout.getChildren().add(silverBonus);
		
		}

		if(client.getGameBoard().getBronzeBonus() != null){
		
			bronzeBonus.setLayoutX(gameLayout.getPrefWidth() * 0.79);
			bronzeBonus.setLayoutY(gameLayout.getPrefHeight() * 0.98);
			bronzeBonus.setRotate(43);
	
			bronzeBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			bronzeBonus.setPreserveRatio(true);
	
			gameLayout.getChildren().add(bronzeBonus);
		
		}

		if(client.getGameBoard().getIronBonus() != null){
		
			metalBonus.setLayoutX(gameLayout.getPrefWidth() * 0.74);
			metalBonus.setLayoutY(gameLayout.getPrefHeight() * 0.99);
			metalBonus.setRotate(43);
	
			metalBonus.setFitHeight(gameLayout.getPrefHeight() * 0.05);
			metalBonus.setPreserveRatio(true);
	
			gameLayout.getChildren().add(metalBonus);
		
		}
	}

	/**
	 * this method add the cities to the game board layout
	 */
	private void addCities() {

		cities = new ArrayList<>();
		edges = new ArrayList<>();

		for (i = 0; i < 15; i++) {

			if (("Gold").equals(client.getGameBoard().getCities().get(i).getCityColor())) {

				cities.add(new CityLayout(goldCity, client.getGameBoard().getCities().get(i), gameLayout,
						numberOfPlayers, client.getGameBoard().getCities().get(i).getCityBonuses()));
			}

			if (("Bronze").equals(client.getGameBoard().getCities().get(i).getCityColor())) {

				cities.add(new CityLayout(bronzeCity, client.getGameBoard().getCities().get(i), gameLayout,
						numberOfPlayers, client.getGameBoard().getCities().get(i).getCityBonuses()));
			}

			if (("Silver").equals(client.getGameBoard().getCities().get(i).getCityColor())) {

				cities.add(new CityLayout(silverCity, client.getGameBoard().getCities().get(i), gameLayout,
						numberOfPlayers, client.getGameBoard().getCities().get(i).getCityBonuses()));
			}

			if (("Iron").equals(client.getGameBoard().getCities().get(i).getCityColor())) {

				cities.add(new CityLayout(ironCity, client.getGameBoard().getCities().get(i), gameLayout,
						numberOfPlayers, client.getGameBoard().getCities().get(i).getCityBonuses()));
			}

			if (("Purple").equals(client.getGameBoard().getCities().get(i).getCityColor())) {

				cities.add(new CityLayout(purpleCity, client.getGameBoard().getCities().get(i), gameLayout,
						numberOfPlayers, client.getGameBoard().getCities().get(i).getCityBonuses()));
			}
		}
		// METTERE IL RE
		for (i = 0; i < client.getGameBoard().getCities().size(); i++) {
			if (cities.get(i).getCityName().getText()
					.equalsIgnoreCase(client.getGameBoard().getKing().getLocationCity().getName())) {
				cities.get(i).addKing();
			}
		}
		// prime 5 città

		cities.get(0).setLayoutX(gameLayout.getPrefWidth() * 0.08);
		cities.get(0).setLayoutY(gameLayout.getPrefHeight() * 0.08);

		cities.get(2).setLayoutX(gameLayout.getPrefWidth() * 0.22);
		cities.get(2).setLayoutY(gameLayout.getPrefHeight() * 0.1);

		cities.get(1).setLayoutX(gameLayout.getPrefWidth() * 0.08);
		cities.get(1).setLayoutY(gameLayout.getPrefHeight() * 0.27);

		cities.get(3).setLayoutX(gameLayout.getPrefWidth() * 0.22);
		cities.get(3).setLayoutY(gameLayout.getPrefHeight() * 0.29);

		cities.get(4).setLayoutX(gameLayout.getPrefWidth() * 0.08);
		cities.get(4).setLayoutY(gameLayout.getPrefHeight() * 0.46);

		// seoconde 5 città

		cities.get(5).setLayoutX(gameLayout.getPrefWidth() * 0.39);
		cities.get(5).setLayoutY(gameLayout.getPrefHeight() * 0.08);

		cities.get(8).setLayoutX(gameLayout.getPrefWidth() * 0.53);
		cities.get(8).setLayoutY(gameLayout.getPrefHeight() * 0.1);

		cities.get(6).setLayoutX(gameLayout.getPrefWidth() * 0.39);
		cities.get(6).setLayoutY(gameLayout.getPrefHeight() * 0.27);

		cities.get(9).setLayoutX(gameLayout.getPrefWidth() * 0.53);
		cities.get(9).setLayoutY(gameLayout.getPrefHeight() * 0.29);

		cities.get(7).setLayoutX(gameLayout.getPrefWidth() * 0.39);
		cities.get(7).setLayoutY(gameLayout.getPrefHeight() * 0.46);

		// seoconde 5 città

		cities.get(10).setLayoutX(gameLayout.getPrefWidth() * 0.7);
		cities.get(10).setLayoutY(gameLayout.getPrefHeight() * 0.08);

		cities.get(13).setLayoutX(gameLayout.getPrefWidth() * 0.84);
		cities.get(13).setLayoutY(gameLayout.getPrefHeight() * 0.1);

		cities.get(11).setLayoutX(gameLayout.getPrefWidth() * 0.7);
		cities.get(11).setLayoutY(gameLayout.getPrefHeight() * 0.27);

		cities.get(14).setLayoutX(gameLayout.getPrefWidth() * 0.84);
		cities.get(14).setLayoutY(gameLayout.getPrefHeight() * 0.29);

		cities.get(12).setLayoutX(gameLayout.getPrefWidth() * 0.7);
		cities.get(12).setLayoutY(gameLayout.getPrefHeight() * 0.46);

		int target = 0;

		for (i = 0; i < client.getGameBoard().getCities().size(); i++) {

			for (int k = 0; k < client.getGameBoard().getCities().get(i).getLinkedCities().size(); k++) {

				for (int j = 0; j < 15; j++) {

					if (client.getGameBoard().getCities().get(i).getLinkedCities().get(k).getName()
							.equals(cities.get(j).getCityName().getText())) {

						target = j;
					}
				}
				edges.add(new EdgeLayout(cities.get(i), cities.get(target)));
			}
		}
		gameLayout.getChildren().addAll(edges);
		gameLayout.getChildren().addAll(cities);
	}

	/**
	 * this method adds the map to the game board layout
	 */
	private void addMap() {
		map.setFitWidth(gameLayout.getPrefWidth());
		map.setFitHeight(gameLayout.getPrefHeight() * 1.138);
		gameLayout.getChildren().add(map);
	}

	/**
	 * this method adds the indicator disk of the players to the game board
	 * layout
	 */
	private void addIndicatorDisks() {

		for (i = 1; i < numberOfPlayers + 1; i++) {

			IndicatorDiskLayout money = new IndicatorDiskLayout(
					client.getGameBoard().getPlayersColor().get(i).toUpperCase(), gameLayout);
			IndicatorDiskLayout points = new IndicatorDiskLayout(
					client.getGameBoard().getPlayersColor().get(i).toUpperCase(), gameLayout);
			IndicatorDiskLayout nobility = new IndicatorDiskLayout(
					client.getGameBoard().getPlayersColor().get(i).toUpperCase(), gameLayout);

			money.setLayoutX(gameLayout.getPrefWidth() * 0.05);
			money.setLayoutY(gameLayout.getPrefHeight() * 1.05 - i * money.getPrefHeight());

			if(Screen.getPrimary().getBounds().getWidth()/Screen.getPrimary().getBounds().getHeight() > 1.5){
				
				money.moveMoneyDiskPc(client.getGameBoard().getProsperityTrackCoordinate().get(i));

				points.movePointsDiskPc(client.getGameBoard().getPointsTrackCoordinate().get(i));
				
				nobility.moveNobilityDiskPc(client.getGameBoard().getNobilityTrackCoordinate().get(i));
				
			} else {
				
				money.moveMoneyDisk(client.getGameBoard().getProsperityTrackCoordinate().get(i));
				points.movePointsDisk(client.getGameBoard().getPointsTrackCoordinate().get(i));
				nobility.moveNobilityDisk(client.getGameBoard().getNobilityTrackCoordinate().get(i));
				
			}
				
			gameLayout.getChildren().add(money);

			points.setLayoutX(gameLayout.getPrefWidth() * 0.025);
			points.setLayoutY(gameLayout.getPrefHeight() * 0.044 - i * money.getPrefHeight());

			gameLayout.getChildren().add(points);

			nobility.setLayoutX(gameLayout.getPrefWidth() * 0.06);
			nobility.setLayoutY(gameLayout.getPrefHeight() * 1 - i * money.getPrefHeight());

			gameLayout.getChildren().add(nobility);

		}
	}

	/**
	 * this method adds the counselors to the balconies
	 */
	private void addCouncilors() {
		seaBalcony = new ArrayList<>();
		ArrayList<Counselor> counselorsSea = new ArrayList<>(
				client.getGameBoard().getBalconies().get(0).getCounselors());
		Collections.reverse(counselorsSea);
		balconyFiller(counselorsSea, seaBalcony);
		balconyDrawer(seaBalcony, 0.025, 0.14, 0.8);

		hillBalcony = new ArrayList<>();
		ArrayList<Counselor> counselorsHill = new ArrayList<>(
				client.getGameBoard().getBalconies().get(1).getCounselors());
		Collections.reverse(counselorsHill);
		balconyFiller(counselorsHill, hillBalcony);
		balconyDrawer(hillBalcony, 0.025, 0.43, 0.8);

		mountainBalcony = new ArrayList<>();
		ArrayList<Counselor> counselorsMountain = new ArrayList<>(
				client.getGameBoard().getBalconies().get(2).getCounselors());
		Collections.reverse(counselorsMountain);
		balconyFiller(counselorsMountain, mountainBalcony);
		balconyDrawer(mountainBalcony, 0.025, 0.76, 0.8);

		kingBalcony = new ArrayList<>();
		ArrayList<Counselor> counselorsKing = new ArrayList<>(client.getGameBoard().getKingBalcony().getCounselors());
		Collections.reverse(counselorsKing);
		balconyFiller(counselorsKing, kingBalcony);
		balconyDrawer(kingBalcony, 0.025, 0.62, 0.85);
	}

	/**
	 * this method add the clear button to the game board
	 */
	private void addClearButton(PlayerChange player){
	
		clear = new AnchorPane();
		clear.setLayoutX(border.getPrefWidth()*0.66);
		clear.setLayoutY(border.getPrefHeight()*0.9);
		clear.setPrefHeight(border.getPrefHeight()*0.05);
		clear.setPrefWidth(border.getPrefWidth()*0.25);
		
		
		clearButton = new Button("Clear Choice");
		clearButton.setDisable(true);
		clearButton.setLayoutX(0);
		clearButton.setLayoutY(0);
		clearButton.setPrefHeight(clear.getPrefHeight());
		clearButton.setPrefWidth(clear.getPrefWidth());

		clearButton.setOnMouseClicked(e -> clearButton.setEffect(new Glow(1)));

		clearButton.setOnAction(e -> {

			sample.play();
			choice = new ArrayList<>();
			showGameBoardLayout();
			showGameSpace();
			showPlayerView(client.getPlayer());
			showPossibleActions(player);
			sample.stop();
		});
		
		clear.getChildren().add(clearButton);

		if(!border.getChildren().contains(clear)){
			border.getChildren().add(clear);
		}
		
	}
	
	/**
	 * this method set the position of the counselors on the game board
	 * 
	 * @param balcony
	 * @param d
	 * @param e
	 * @param f
	 */
	private void balconyDrawer(ArrayList<ImageView> balcony, double d, double e, double f) {

		double width = gameLayout.getPrefWidth() * e;

		for (i = 0; i < balcony.size(); i++) {
			balcony.get(i).setPreserveRatio(true);
			balcony.get(i).setFitWidth(gameLayout.getPrefWidth() * d);
			if (i > 0) {
				width = balcony.get(i - 1).getLayoutX() + balcony.get(i - 1).getFitWidth();
			}
			balcony.get(i).setLayoutX(width);
			balcony.get(i).setLayoutY(gameLayout.getPrefHeight() * f);
			gameLayout.getChildren().add(balcony.get(i));
		}
	}

	/**
	 * this method put in the balcony counselors images based on the colors of
	 * the arraylist of counselor passes
	 * 
	 * @param counselors
	 *            the counselors from where to pick the color
	 * @param balcony
	 *            the arraylist of imageview where to put images
	 */
	private void balconyFiller(List<Counselor> counselors, List<ImageView> balcony) {
		for (i = 0; i < counselors.size(); i++) {
			if ((BLACK).equals(counselors.get(i).getColor())) {
				balcony.add(new ImageView(blackCounselor));
			}
			if ((PINK).equals(counselors.get(i).getColor())) {
				balcony.add(new ImageView(pinkCounselor));
			}
			if ((BLUE).equals(counselors.get(i).getColor())) {
				balcony.add(new ImageView(blueCounselor));
			}
			if ((ORANGE).equals(counselors.get(i).getColor())) {
				balcony.add(new ImageView(orangeCounselor));
			}
			if ((WHITE).equals(counselors.get(i).getColor())) {
				balcony.add(new ImageView(whiteCounselor));
			}
			if ((PURPLE).equals(counselors.get(i).getColor())) {
				balcony.add(new ImageView(purpleCounselor));
			}
		}
	}

	/**
	 * this method adds the regions to the game board layout
	 */
	private void addRegions() {

		// DA IMPLEMENTARE CON ALTRE IMMAGINI
		regions = new ArrayList<>();

		regions.add(seaDeck);
		regions.add(hillsDeck);
		regions.add(mountainsDeck);
		regions.add(king);

		seaDeck.setLayoutX(gameLayout.getPrefWidth() * 0.075);

		seaDeck.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		seaDeck.setFitWidth(gameLayout.getPrefWidth() * 0.06);
		seaDeck.setPreserveRatio(true);

		gameLayout.getChildren().add(regions.get(0));

		hillsDeck.setLayoutX(gameLayout.getPrefWidth() * 0.37);
		hillsDeck.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		hillsDeck.setFitWidth(gameLayout.getPrefWidth() * 0.06);
		hillsDeck.setPreserveRatio(true);

		gameLayout.getChildren().add(regions.get(1));

		mountainsDeck.setLayoutX(gameLayout.getPrefWidth() * 0.7);
		mountainsDeck.setLayoutY(gameLayout.getPrefHeight() * 0.69);

		mountainsDeck.setFitWidth(gameLayout.getPrefWidth() * 0.06);
		mountainsDeck.setPreserveRatio(true);

		gameLayout.getChildren().add(regions.get(2));

		king.setLayoutX(gameLayout.getPrefWidth() * 0.55);
		king.setLayoutY(gameLayout.getPrefHeight() * 0.86);

		king.setFitWidth(gameLayout.getPrefWidth() * 0.05);
		king.setPreserveRatio(true);

		gameLayout.getChildren().add(regions.get(3));
	}

	/**
	 * this method updates the rooms, during the room selection. it is called by
	 * the connection when a new room is available
	 */
	@Override
	public void updateRooms() {

		if (availableRooms != null) {
			availableRooms.getItems().addAll(Client.getInstance().getRoomsList());
		}
	}

	/**
	 * this method shows an alert box when the game finishes
	 */
	public void showWinner() {

		if (client.getGameBoard().getWinnerPlayerNumber() != 0) {

			if (client.getGameBoard().getWinnerPlayerNumber() == client.getPlayer().getPlayerNumber()) {
				alert("HAI VINTO!");

			} else
				alert("Hai perso, ha vinto il giocatore " + client.getGameBoard().getWinnerPlayerNumber());
		}
	}
}