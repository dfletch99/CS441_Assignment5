package com.mygdx.clicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Arrays;

public class MainGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture plate;
	private Texture settings;
	private Texture howtoplay;
	private BitmapFont text;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

	private boolean[] screens;

	private int garbagePlates, clickAmount;
	int w, h;
	@Override
	public void create () {
		garbagePlates = 0;
		clickAmount = 1;

		batch = new SpriteBatch();
		plate = new Texture("plate.png");
		settings = new Texture("settings_foreground.png");
		howtoplay = new Texture("howtoplay_foreground.png");
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
		screens[0] = true;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("gamefont.TTF"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 10;
		parameter.kerning = true;
		text = generator.generateFont(parameter);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {
		if(screens[0]){ //main menu
			Gdx.gl.glClearColor(1, 0.55f, 0.2f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			parameter.size = 200;
			text = generator.generateFont(parameter);
			//title
			text.draw(batch, "GP CLICKER!", 425, 950);
			//play button
			batch.draw(plate, 200, 500, 250, 250);
			parameter.size = 150;
			text = generator.generateFont(parameter);
			text.draw(batch, "play!", 475, 650);
			//settings button
			batch.draw(settings, 155, 215, 325, 325);
			text.draw(batch, "settings", 465, 400);
			//how to play button
			batch.draw(howtoplay, 165, -25, 300, 300);
			text.draw(batch, "how  to  play", 475, 150);
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
			}
		}
		else if(screens[1]){ //instructions

		}
		else if(screens[2]){ //main click
			Gdx.gl.glClearColor(1, 0.55f, 0.2f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			if(Gdx.input.isTouched()) {
				int x = Gdx.input.getX();
				int y = Gdx.input.getY();
				if(x >= 175 && x <= 965 && y >= 65 && y <= 840) {
					batch.draw(plate, 200, 250, 750, 750);
					if(Gdx.input.justTouched()){
						garbagePlates += clickAmount;
					}
				}
				else{
					batch.draw(plate, 150, 200, 850, 850);
				}
			}
			else{
				batch.draw(plate, 150, 200, 850, 850);
			}
			parameter.size = 75;
			text = generator.generateFont(parameter);
			text.draw(batch, "Garbage Plates", 235, 180);
			text.draw(batch, Integer.toString(garbagePlates), 235, 100);
			batch.end();
		}
		else if(screens[3]){ //shop

		}
		else if(screens[4]){ //the ultimate purchase

		}
		else if(screens[5]){ //victory

		}
		else if(screens[6]){ //settings
			Gdx.gl.glClearColor(1, 0.55f, 0.2f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.end();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		plate.dispose();
		generator.dispose();
		text.dispose();
	}

	@Override
    public void resize(int w, int h){
		this.w = w;
		this.h = h;
    }
}
