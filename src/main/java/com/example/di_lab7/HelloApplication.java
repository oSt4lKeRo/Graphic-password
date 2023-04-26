package com.example.di_lab7;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HelloApplication extends Application {

	class Elem{
		ImageView imageView;
		boolean status;
		int i, j;

		Elem(Image image, int i, int j){
			this.imageView = new ImageView(image);
			this.status = false;
			this.i = i;
			this.j = j;
		}
	}
	
	class Answer{
		ArrayList<Integer> arrayList;
		String str;
		
		Answer(ArrayList<Integer> arrayList, String str){
			this.arrayList = arrayList;
			this.str = str;
		}
	}

	public static void clear(Elem[][] elemMas, Image image){
		for(Elem[] elems : elemMas){
			for(Elem elem : elems){
				elem.imageView.setImage(image);
				elem.status = false;
			}
		}
	}
	
	public static void printArray(ArrayList<Integer> arrays){
		for(int i : arrays){
			System.out.print(i + " ");
		}
		System.out.println();
	}




	public int count = 0;
	
	
	@Override
	public void start(Stage stage) throws IOException {

		Answer[] answers = new Answer[2];
		ArrayList<Integer> array = new ArrayList<>();
		array.add(0);
		array.add(0);
		array.add(0);
		array.add(1);
		array.add(1);
		array.add(2);
		Collections.sort(array);
		Answer answer = new Answer(array, "Dima");
		answers[0] = answer;
		
		array = new ArrayList<>();
		array.add(0);
		array.add(0);
		Collections.sort(array);
		answer = new Answer(array, "Kola");
		answers[1] = answer;





		BorderPane borderPane = new BorderPane();

		Image whiteBox = new Image("C:\\material\\lab3\\WhiteBox.png");
		Image greenBox = new Image("C:\\material\\lab3\\GreenBox.png");

		ArrayList<Integer> arrayList = new ArrayList<>();
		Elem[][] elemMas = new Elem[10][10];
		for(int i = 0; i < elemMas.length; i++){
			for(int j = 0; j < elemMas.length; j++){

				Elem elem = new Elem(whiteBox, i, j);
				EventHandler eventHandler = new EventHandler() {
					@Override
					public void handle(Event event) {
						if(!elem.status){
							elem.imageView.setImage(greenBox);
							elem.status = true;
							arrayList.add(elem.i);
							arrayList.add(elem.j);
						} else {
							elem.imageView.setImage(whiteBox);
							elem.status = false;
							arrayList.remove(elem.i);
							arrayList.remove(elem.j);
						}
						Collections.sort(arrayList);
					}
				};
				elem.imageView.setX(i * 40+2);
				elem.imageView.setY(j * 40+2);
				elem.imageView.setOnMouseClicked(eventHandler);
				elemMas[i][j] = elem;
				borderPane.getChildren().add(elem.imageView);
			}
		}

		Button clear = new Button("Clear");
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				clear(elemMas, whiteBox);
				arrayList.clear();
			}
		});
		clear.setTranslateX(-225);
		clear.setTranslateY(25);

		borderPane.setTranslateX(750);
		borderPane.setTranslateY(75);



		Text name = new Text("Name:");
		name.setTranslateX(45);
		name.setTranslateY(47.5);
		Font font = new Font(18);
		name.setFont(font);

		TextField textField = new TextField();
		textField.setMaxWidth(150);
		textField.setTranslateX(50);
		textField.setTranslateY(50);

		Button start = new Button("Start");
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Answer answer1 = new Answer(arrayList, textField.getText());
				boolean check = false;
				for(Answer i : answers){
					if(i.arrayList.equals(answer1.arrayList) && i.str.equals(answer1.str)){
						check = true;
						Text text = new Text("Ура");
						BorderPane borderPane1 = new BorderPane();
						borderPane1.setCenter(text);
						Scene scene = new Scene(borderPane1, 800, 600);
						stage.setScene(scene);
						System.out.println("Ура");
						break;
					}
				}
				if(!check){
					System.out.println("Неправильно, осталось попыток: " + (3 - count));
					count++;
				}
				if(count == 3){
					System.out.println("Попытки закончились, ждите 5 сек");
					try {
						count = 0;
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					System.out.println("Можете снова попытаться взломать нас");
				}
			}
		});

		start.setTranslateX(125);
		start.setTranslateY(100);

		BorderPane leftPane = new BorderPane();
		leftPane.setLeft(name);
		leftPane.setRight(textField);
		leftPane.setBottom(start);

		leftPane.setTranslateX(100);
		leftPane.setTranslateY(150);



		BorderPane borderPane1 = new BorderPane();
		borderPane1.getChildren().add(borderPane);
		borderPane1.setRight(clear);
		borderPane1.getChildren().add(leftPane);

		Scene scene = new Scene(borderPane1, 1200, 600);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}