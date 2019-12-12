package com.mygdx.clicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MainGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture plate, settings, howtoplay, chef, hand, kitchen, restaurant, madden;
	private BitmapFont text50, text75, text100, text150, text200;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

	private boolean[] screens;
	private float[] colorSettings;
	private int garbagePlates, clickAmount, earnRate60, earnRate30, earnRate10, earnRate5, earnRate1;
	private int globalCounter;
	int w, h;
	@Override
	public void create () {
		garbagePlates = 0;
		clickAmount = 1;
		earnRate60 = 0;
		earnRate30 = 0;
		earnRate10 = 0;
		earnRate5 = 0;
		earnRate1 = 0;
		globalCounter = 0;

		batch = new SpriteBatch();
		plate = new Texture("plate.png");
		settings = new Texture("settings_foreground.png");
		howtoplay = new Texture("howtoplay_foreground.png");
		chef = new Texture("chef.png");
		hand = new Texture("hand.png");
		kitchen = new Texture("kitchen.png");
		restaurant = new Texture("restaurant.png");
		madden = new Texture("madden.png");
		//screen manager. yep, just an array of booleans, thats all you need.
		/*
		0 - main menu
		1 - instructions
		2 - main click screen
		3 - buy screen
		4 - the Ultimate Purchase
		5 - victory
		6 - settings
		 */
		screens = new boolean[7];
		colorSettings = new float[3];
		//default background color
        colorSettings[0] = 1f;
        colorSettings[1] = .55f;
        colorSettings[2] = .2f;
		screens[0] = true;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("gamefont.TTF"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 10;
		parameter.kerning = true;
		parameter.size = 50;
		text50 = generator.generateFont(parameter);
		parameter.size = 75;
		text75 = generator.generateFont(parameter);
		parameter.size = 100;
		text100 = generator.generateFont(parameter);
		parameter.size = 150;
		text150 = generator.generateFont(parameter);
		parameter.size = 200;
		text200 = generator.generateFont(parameter);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		if(screens[0]){ //main menu
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			//title
			text200.draw(batch, "GP CLICKER!", 425, 950);
			//play button
			batch.draw(plate, 200, 500, 250, 250);
			text150.draw(batch, "play!", 475, 650);
			//settings button
			batch.draw(settings, 155, 215, 325, 325);
			text150.draw(batch, "settings", 465, 400);
			//how to play button
			batch.draw(howtoplay, 165, -25, 300, 300);
			text150.draw(batch, "how  to  play", 475, 150);
			batch.end();

			if(Gdx.input.justTouched()){
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				//play button pressed
				if(x >= 220 && x <= 930 && y >= 340 && y <= 565){
					screens[0] = false;
					screens[2] = true;
				}
				//settings button pressed
				if(x >= 207 && x <= 1200 && y >= 600 && y <= 810){
					screens[0] = false;
					screens[6] = true;
				}
				//instructions button pressed
				if(x >= 210 && x <= 1380 && y >= 840 && y <= 1060){
					screens[0] = false;
					screens[1] = true;
				}
			}
		}
		else if(screens[1]){ //instructions
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			//back to main menu text
			text75.draw(batch, "back to menu", 1450, 1050);
			text75.draw(batch, garbagePlates + " gp", 25, 1050);
			//instructions
			text100.draw(batch, "keep  on  clicking  the  plate!", 50, 875);
			batch.draw(plate, 1650, 700, 300, 300);
			text100.draw(batch, "buy plate generating power at the", 50, 650);
			text150.draw(batch, "shop", 800, 550);
			text100.draw(batch, "change your style in ", 420, 400);
			text150.draw(batch, "settings", 650, 300);
			text100.draw(batch, "earn enough plates to", 50, 150);
			text200.draw(batch, "win!", 1475, 165);
			batch.end();

			if(Gdx.input.justTouched()) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				System.out.println(x + " " + y);
				//menu button clicked
				if(x >= 1454 && x <= 1990 && y >= 15 && y <= 70){
					screens[1] = false;
					screens[0] = true;
				}
				//plate clicked
				if(x >= 1670 && x <= 1940 && y >= 100 && y <= 350){
					garbagePlates += clickAmount;
					screens[1] = false;
					screens[2] = true;
				}
				//shop clicked
                if(x >= 805 && x <= 1165 && y >= 515 && y <= 600){
                    screens[1] = false;
                    screens[3] = true;
                }
                //settings clicked
				if(x >= 655 && x <= 1380 && y <= 845 && y >= 770){
					screens[1] = false;
					screens[6] = true;
				}
				//win clicked
				if(x >= 1475 && x <= 1930 && y >= 900 && y <= 1000){
					screens[1] = false;
					screens[4] = true;
				}
			}
		}
		else if(screens[2]){ //main click
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();

			//main garbage plate section
			text75.draw(batch, "Garbage Plates", 235, 180);
			text75.draw(batch, Integer.toString(garbagePlates), 235, 100);

			//shop text
			text150.draw(batch, "shop", 1100, 850);
			//settings text
			text150.draw(batch, "settings", 1100, 550);
			//instructions text
			text150.draw(batch, "how to play", 1100, 250);
			//back to main menu text
			text75.draw(batch, "back to menu", 1450, 1050);

			if(Gdx.input.isTouched()){
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				if(x >= 175 && x <= 965 && y >= 65 && y <= 840){
					//garbage plate touched
					if(Gdx.input.justTouched()){
						garbagePlates += clickAmount;
					}
					batch.draw(plate, 200, 250, 750, 750);
				}
				else{
                    batch.draw(plate, 150, 200, 850, 850);
                }
				if(Gdx.input.justTouched()) {
					//shop button clicked
					if(x >= 1100 && x <= 1460 && y >= 220 && y <= 300){
						screens[2] = false;
						screens[3] = true;
					}
					//settings button clicked
					else if(x >= 1106 && x <= 1823 && y >= 510 && y <= 600){
						screens[2] = false;
						screens[6] = true;
					}
					//instructions button clicked
					else if(x >= 1100 && x <= 1970 && y >= 810 && y <= 900){
						screens[2] = false;
						screens[1] = true;
					}
					//menu button clicked
					else if(x >= 1454 && x <= 1990 && y >= 15 && y <= 70){
						screens[2] = false;
						screens[0] = true;
					}
				}
			}
            else{
                batch.draw(plate, 150, 200, 850, 850);
            }
			batch.end();
		}
		else if(screens[3]){ //shop
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			//back to game text
			text75.draw(batch, "back to game", 1450, 1050);
			//display gp
			text75.draw(batch, garbagePlates + " gp", 50, 50);

			//draw chef stuff
			batch.draw(chef, 50, 700, 450,450);
			text75.draw(batch, "hire a chef", 50, 750);
			text75.draw(batch, "costs 100", 50, 670);
			text75.draw(batch, "1 gpps", 50, 590);

			//draw hand stuff
			batch.draw(hand, 75, 250, 350, 350);
			text75.draw(batch, "enhance", 50, 290);
			text75.draw(batch, "costs 75", 50, 210);
			text75.draw(batch, "better click", 50, 130);

			//draw kitchen stuff
			batch.draw(kitchen, 600, 575, 700, 700);
			text75.draw(batch, "new kitchen", 700, 750);
			text75.draw(batch, "costs 500", 700, 670);
			text75.draw(batch, "6 gpps", 700, 590);

			//draw restaurant stuff
			batch.draw(restaurant, 730, 330, 450, 200);
			text75.draw(batch, "restaurant", 700, 290);
			text75.draw(batch, "costs 10000", 700, 210);
			text75.draw(batch, "150 gpps", 700, 130);

			//the ultimate purchase
			text200.draw(batch, "the", 1400, 680);
			text150.draw(batch, "ultimate", 1250, 560);
			text150.draw(batch, "purchase", 1250, 460);

			batch.end();

			if(Gdx.input.justTouched()) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				//menu button clicked
                if(x >= 1454 && x <= 1990 && y >= 15 && y <= 70){
					screens[3] = false;
					screens[2] = true;
				}
                //chef clicked
				if(x >= 50 && x <= 535 && y >= 20 && y <= 530){
					if(garbagePlates >= 100){
						garbagePlates -= 100;
						earnRate60 += 1;
					}
				}
				//hand clicked
				if(x >= 50 && x <= 410 && y >= 565 && y <= 985){
					if(garbagePlates >= 75){
						garbagePlates -= 75;
						clickAmount++;
					}
				}
				//kitchen clicked
				if(x >= 715 && x <= 1175 && y >= 35 && y <= 520){
					if(garbagePlates >= 500){
						garbagePlates -= 500;
						earnRate10++;
					}
				}
				//restaurant clicked
				if(x >= 710 && x <= 1170 && y >= 550 && y <= 975){
					if(garbagePlates >= 10000){
						garbagePlates -= 10000;
						earnRate1 += 2; //120
						earnRate5 += 2; //24
						earnRate10 += 1; //6
					}
				}

				//ultimate purchase
				if(x >= 1255 && x <= 1990 && y >= 380 && y <= 960){
					screens[3] = false;
					screens[4] = true;
				}
			}
		}
		else if(screens[4]){ //the ultimate purchase
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			//back to main menu text
			text75.draw(batch, "back to game", 1450, 1050);
			text75.draw(batch, garbagePlates + " gp", 25, 1050);

			text150.draw(batch, " The    Ultimate    Purchase", 50, 900);
			text200.draw(batch, "a", 270, 700);
			text200.draw(batch, "good", 100, 550);
			text200.draw(batch, "grade", 50, 400);
			text75.draw(batch, "only 100000", 70, 270);
			text75.draw(batch, "plates!", 140, 200);
			text100.draw(batch, "click", 1500, 600);
			text100.draw(batch, "yourself", 1400, 525);
			text100.draw(batch, "to", 1600, 450);
			text200.draw(batch, "buy!", 1475, 350);
			text50.draw(batch, "please", 1775, 35);

			batch.draw(madden, 600, 0,800, 800);
			batch.end();

			if(Gdx.input.justTouched()) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				System.out.println(x + " " + y);
				//back to game
				if(x >= 1454 && x <= 1990 && y >= 15 && y <= 70){
					screens[4] = false;
					screens[2] = true;
				}
				//clicked
				if(x >= 665 && x <= 1330 && y >= 280 && y <= 1000){
					if(garbagePlates >= 100000) {
						garbagePlates = 0;
						earnRate60 = 0;
						earnRate30 = 0;
						earnRate10 = 0;
						earnRate5 = 0;
						earnRate1 = 0;
						screens[4] = false;
						screens[5] = true;
					}
				}
			}
		}
		else if(screens[5]){ //victory
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			text200.draw(batch, "congratulations!", 50, 1000);
			text200.draw(batch, "you showed mercy!", 75, 850);
			text100.draw(batch, "click    the    plate    to    start    again", 80, 600);
			text200.draw(batch, "exit", 1300, 400);
			text200.draw(batch, "game", 1300, 250);
			batch.draw(plate, 300, 5, 550, 550);
			batch.end();

			if(Gdx.input.justTouched()) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				System.out.println(x + " " + y);
				//restart
                if(x >= 355 && x <= 810 && y >= 575 && y <= 1020){
                    clickAmount = 1;
                    screens[5] = false;
                    screens[0] = true;
                }
                //exit game
                if(x >= 1320 && x <= 1755 && y >= 655 && y <= 923){
                    Gdx.app.exit();
                }
			}
		}
		else if(screens[6]){ //settings
			Gdx.gl.glClearColor(colorSettings[0], colorSettings[1], colorSettings[2], 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			//back to main menu text
			text75.draw(batch, "back to menu", 1450, 1050);
			//main text
			text200.draw(batch, "choose your style!", 35, 900);
			//style texts
            text150.draw(batch, "bright", 225, 600);
            text150.draw(batch, "smooth", 225, 350);
            text150.draw(batch, "default", 1050, 600);
            text150.draw(batch, "sweet", 1050, 350);
			batch.end();

			if(Gdx.input.justTouched()) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				System.out.println(x + " " + y);
                //back to menu pressed
				if(x >= 1454 && x <= 1990 && y >= 15 && y <= 70){
					screens[6] = false;
					screens[0] = true;
				}
				//light theme
                if( x >= 220 && x <= 760 && y >= 460 && y <= 550){
                    colorSettings[0] = .9f;
                    colorSettings[1] = .9f;
                    colorSettings[2] = .9f;
                }
                //default theme
                if( x >= 1055 && x <= 1680 && y >= 460 && y <= 550){
                    colorSettings[0] = 1f;
                    colorSettings[1] = .55f;
                    colorSettings[2] = .2f;
                }
                //smooth theme
                if( x >= 225 && x <= 775 && y >= 715 && y <= 800){
                    colorSettings[0] = 0.6f;
                    colorSettings[1] = 0;
                    colorSettings[2] = 1f;
                }
                //sweet theme
                if( x >= 1055 && x <= 1500 && y >= 708 && y <= 800){
                    colorSettings[0] = 0.6f;
                    colorSettings[1] = 0.75f;
                    colorSettings[2] = 1f;
                }
			}
		}
		autoClick();
	}

	private void autoClick() {
		globalCounter++;
		if(globalCounter%60 == 0){
			garbagePlates += earnRate60;
		}
		if(globalCounter%30 == 0){
			garbagePlates += earnRate30;
		}
		if(globalCounter%10 == 0){
			garbagePlates += earnRate10;
		}
		if(globalCounter%5 == 0){
			garbagePlates += earnRate5;
		}
		garbagePlates += earnRate1;
		if(globalCounter == 60){
			globalCounter = 0;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		plate.dispose();
		settings.dispose();
		howtoplay.dispose();
		generator.dispose();
		chef.dispose();
		hand.dispose();
		kitchen.dispose();
		restaurant.dispose();
		madden.dispose();
		text50.dispose();
		text75.dispose();
		text100.dispose();
		text150.dispose();
		text200.dispose();
	}

	@Override
    public void resize(int w, int h){
		this.w = w;
		this.h = h;
    }
}
