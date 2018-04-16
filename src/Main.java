
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    /* setter opp variabler utenfor primaryStage(window) for
    ** å kunne bruke de i metodene som ligger utenfor
    */

    Stage window;
    Scene start, spm, finish;

    int count = 0;
    int correct = 0;
    int questionNum = 1;

    Label score;
    Label question;
    Label finishLbl;
    ImageView image;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //bytter til kortere og enklere navn
        window = primaryStage;

        //start - SCENE1

        //labels, button og image
        Label headLbl = new Label("Welcome to the quiz!");
        headLbl.setStyle("-fx-font-size: 30px;" +
                         "-fx-text-fill: #F2AF29;");
        headLbl.setPadding(new Insets(0, 0, 5, 30));
        Label quoteLbl = new Label("may the force be with you..");
        quoteLbl.setStyle("-fx-font-size: 15px;" +
                          "-fx-text-fill: #AB343E;");
        quoteLbl.setPadding(new Insets(5, 0, 5, 85));
        Button button1 = new Button("Start");
        ImageView starwars = new ImageView(new Image(getClass().getResourceAsStream("/res/starwars.png")));
        starwars.setFitHeight(200);
        starwars.setFitWidth(355);
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 15, 3));
        grid.setConstraints(headLbl, 1, 10);
        grid.setConstraints(starwars, 1, 20);
        grid.setConstraints(quoteLbl, 1, 30);
        grid.setConstraints(button1, 1, 40);
        grid.getChildren().addAll(headLbl, starwars, quoteLbl, button1);
        //lager scene og knappfunksjonen
        start = new Scene(grid, 380, 310);
        start.getStylesheets().add("style.css");
        button1.setOnAction(e -> window.setScene(spm));

        //spm - SCENE2

        //labels, button, images og textfield
        question = new Label("What kind of race is Chewbacca?");
        question.setStyle("-fx-font-size: 16px;" +
                          "-fx-text-fill: #E0E0CE;");
        score =  new Label(correct + "/" + count);
        score.setStyle("-fx-font-size: 15px;" +
                       "-fx-text-fill: #E0E0CE;");
        TextField answer = new TextField();
        Button button2 = new Button("Answer");
        //lager imageView til image
        image = new ImageView();
        image.setFitHeight(200);
        image.setFitWidth(355);
        Image chewbacca = new Image(getClass().getResourceAsStream("/res/chewbacca.jpeg"));
        image.setImage(chewbacca);
        //grid
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 10, 10, 10));
        grid2.setConstraints(question, 1, 10);
        grid2.setConstraints(image, 1, 20);
        grid2.setConstraints(answer, 1, 30);
        grid2.setConstraints(button2, 1, 40);
        grid2.setConstraints(score, 1, 50);
        grid2.getChildren().addAll(question, image, answer, button2, score);
        //lager scene og knappfunksjonen
        spm = new Scene(grid2, 380, 310);
        spm.getStylesheets().add("style.css");
        button2.setOnAction(e -> {
            isAnswerCorrect(answer, questionNum);
            score.setText(correct + "/" + count);
            nextQuestion();
            answer.setText("");
            if(correct == 1)
                finishLbl.setText("Congratulations, you got " + correct + " point!");
            else
                finishLbl.setText("Congratulations, you got " + correct + " points!");
            if(count == 5) {
                window.setScene(finish);
            }
        });

        //finish - SCENE3

        //label og button
        finishLbl = new Label();
        finishLbl.setStyle("-fx-font-size: 20px;" +
                "-fx-text-fill: #F2AF29;");
        Button button3 = new Button("Try again!");
        //grid
        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        grid3.setPadding(new Insets(10, 10, 10, 10));
        grid3.setConstraints(finishLbl, 2, 1);
        grid3.setConstraints(button3, 2, 100);
        finishLbl.setPadding(new Insets(0, 0, 20, 0));
        grid3.getChildren().addAll(finishLbl, button3);
        //lager scene og knappfunksjonen
        finish = new Scene(grid3, 380, 310);
        finish.getStylesheets().add("style.css");
        button3.setOnAction(e -> {
            question.setText("What kind of race is Chewbacca?");
            image.setImage(chewbacca);
            count = 0;
            correct = 0;
            score.setText("0/0");
            questionNum = 1;
            window.setScene(start);
        });

        //setter navn på primaryStage, velger startside, og viser den med show()
        window.setTitle("Quiz");
        window.setScene(start);
        window.show();
    }

    //metode som bytter ut spørsmål og bilde i SCENE2
    private void nextQuestion() {
        questionNum++;

        switch(questionNum) {
            case 2:
                question.setText("Who kills Jabba The Hutt?");
                Image jabba = new Image(getClass().getResourceAsStream("/res/jabba.jpeg"));
                image.setImage(jabba);
                break;
            case 3:
                question.setText("What is the name of Han Solo’s ship?");
                Image mf = new Image(getClass().getResourceAsStream("/res/mf.jpg"));
                image.setImage(mf);
                break;
            case 4:
                question.setText("Who is Luke Skywalker’s dad?");
                Image anakin = new Image(getClass().getResourceAsStream("/res/anakin.jpg"));
                image.setImage(anakin);
                break;
            case 5:
                question.setText("What was Luke Skywalker’s original surname?");
                Image luke = new Image(getClass().getResourceAsStream("/res/luke.jpg"));
                image.setImage(luke);
                break;
        }

    }

    /* metode som sjekker om svaret er riktig, og at det
    ** riktige svaret er gitt til det gjeldende spørsmålet
    */
    private void isAnswerCorrect(TextField text, int questionNum) {
        String answer = text.getText().trim().toLowerCase() + "_" + questionNum;

        switch(answer) {
            case "wookie_1":
                count += 1;
                correct += 1;
                break;
            case "princess leia_2":
                count += 1;
                correct += 1;
                break;
            case "millennium falcon_3":
                count += 1;
                correct += 1;
                break;
            case "anakin skywalker_4":
                count += 1;
                correct += 1;
                break;
            case "starkiller_5":
                count += 1;
                correct += 1;
                break;
            default:
                count += 1;
                break;
        }
    }
}
