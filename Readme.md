## What is this project?
This project is a game that we are creating for our University's CMSC 355 Class - Software Engineering: Specification & Design.

## How can I run this application?
### Directions for Android Studio:
Clone this project to any directory you want on your computer using the clone command:

```git clone https://github.com/vcu-cmsc355-fall2018/project-tsc.git```

Then open Android Studio, click File > Open > path/to/project-tsc/.

Once you have the project opened in Android Studio, click File > Sync Project with Gradle Files.

This will sync your project with the gradle files in the project, making sure everything is set to
build.

If you have an android device like a cell phone or tablet, plug that into your computer via USB,
(Make sure you have the android device enabled for USB Debugging!), click the green triangle on the
top right of the screen, wait for the IDE to install the app on your phone and the app will boot
right up.

If you have an Intel processor or are running linux KVM, when you click the green triangle on the
top right of the screen, it will prompt you to create your own virtual android device.

# Iteration 1:

### Synopsis:

The three user stories we completed for the first iteration were the State System, Main Menu State,
and End Screen State. We chose these three because they naturally fit together, and were relatively
easy to make make-shift non-JUnit tests for to make sure they worked, and because everything else
essentially stems from those portions of the app.

The main point of this iteration is to get most of the boiler plate code out of the way. Making sure
that our state system works as planned means we don't have to worry about that in later iterations;
meaning we can focus on game play mechanics and other artistic styling for the game
instead of working out the kinks and bugs of different game states as we go.

Creating the states was not super challenging, the hardest part was getting the JUnit tests working.
This is because LibGdx's dependence on OpenGL does not "sit well" with Mockito, in the sense that
when we mocked things like sprite batches, buttons, etc., it would constantly throw errors relating
to OpenGL objects. We've seen what I can only assume is every error short of a blue screen, and
been stuck on "error compiling shader" for hours. We solved our errors and finally got our first
Mockito-safe test pass by mocking quite literally everything in the test class.
And we mean everything.

### Pictures:

## DISCLAIMER!!! - The art seen in the pictures is placeholder art for proof of concept!

![MainMenu State](https://cdn.discordapp.com/attachments/493900790004580363/501532675480223765/Screenshot_20181014-141439_coffeerun.jpg "Main menu state of the game.")

This is the main menu. It contains a play button, place for a title to come when we get more art,
an options button, and high-score button. (High score button takes the user to th end screen as of
the current moment because high scores haven't been created in this iteration).

![Playing State](https://cdn.discordapp.com/attachments/493900790004580363/501532676474535939/Screenshot_20181014-141455_coffeerun.jpg "Playing state of the game.")

This is the playing state. It has a red background as a placeholder until we get more art, and a
pause button which will lead the user to the pause menu.

![Pause State](https://cdn.discordapp.com/attachments/493900790004580363/501532677397020672/Screenshot_20181014-141501_coffeerun.jpg "Paused state of the game.")

This is the paused state. It has a button to see the options for the game, a button to go back to
the main menu (Bottom left) and a button to return to the game play.

![Options State](https://cdn.discordapp.com/attachments/493900790004580363/501532676474535936/Screenshot_20181014-141448_coffeerun.jpg "Options state of the game.")

This is the options state. It has one button at the moment for going back to the previous state.
This will later contain sliders and options about the game that the user can edit.

![End State](https://cdn.discordapp.com/attachments/493900790004580363/501532676944166913/Screenshot_20181014-141512_coffeerun.jpg "End screen of the game.")

This is the end state. This is what the user is prompted with after finishing a level. This state
can be reached by going into the playing state and waiting a few seconds, or hitting the high scores
button on the main menu. This behavior is specific to this iteration only and all subsequent
iterations, this state will only be accessible through the playing state.

---------------------------------------------------------------------------------------------------------------------------------------------------

# Iteration 2:

### Synopsis:

The three user stories that we completed this iteration were the "Shot o' Espresso", "Coffee", and
"Jumping" user stories.  These stories went well together, because they flesh out the actual level
or world that the player is in. And for an endless runner, they were the three most necessary
components.

This iteration mostly focused on getting the world down, or at least the components to be able to
get the world down. The jumping took quite a while as we went with writing our own version of AABB
collision detection for our objects as opposed to using Box2D collision detection. There are still
a few small bugs with our collision detection but for 99% of cases it works just fine. We will work
on fixing these bugs in the next iteration. Coffee and Espresso were not hard to figure out
and get working.

### Video:
https://youtu.be/f-U3CY1VhT8

### Pictures:

## DISCLAIMER!!! - Some of the art seen in the pictures is placeholder art for proof of concept!

![Playing State](https://cdn.discordapp.com/attachments/493900790004580363/510874088017887236/Screenshot_20181110-124943_coffeerun.jpg "New level of game!")

Here you can see that there is now a player, background, and energy bar in the playing state for the game.

![Items](https://cdn.discordapp.com/attachments/493900790004580363/510874088932114442/Screenshot_20181110-124958_coffeerun.jpg "Items and obstacles!")

Here you can see that there are two items (Coffee and Espresso) and obstacles (Bike rack and brick wall) in the level!

![Collecting Items](https://cdn.discordapp.com/attachments/493900790004580363/510874089427304451/Screenshot_20181110-125023_coffeerun.jpg "Coffee item collected!")

Here you can see that the player is able to collect items and collide with the obstacles.

---------------------------------------------------------------------------------------------------------------------------------------------------

# Iteration 3:

### Synopsis:

The three user stories that we completed this iteration were the "Pillow", "Pausing State", and
"Trashcan" user stories. Pillow and trashcan were fairly convenient to finish, because they were
similar to both the Espresso and the Coffee. They are also conceptually similar in behavior so they
were easy to implement quickly. The Pausing State user story was already started in a previous
Iteration, so we just went ahead and finished this up.

As stated above, Pillow and Trashcan were not hard to implement, though we did have problems with
the unit tests. Pausing State was essentially finishing the state itself, which was already almost
fully complete. Our collision bugs were also fixed during this iteration.

The Linter also does not appreciate the words "collider", and "hitbox." It states that they are
misspelled but it should be fine for our use case. The Linter also wants there to be final
declarations everywhere that doesn't work for our use cases. Finally, the Linter wants static
references to GameTest that won't do anything but break the test code.

### Video
https://youtu.be/f0A1liVSjeA

## DISCLAIMER!!! - Some of the art seen in the pictures is placeholder art for proof of concept!

![Main Menu](https://cdn.discordapp.com/attachments/493900790004580363/518510250538565634/Screenshot_20181201-143234_coffeerun.jpg "Main Menu!")

Here you can see the new art and layout of the main menu screen!

![Obstacles](https://cdn.discordapp.com/attachments/493900790004580363/518510251784404992/Screenshot_20181201-143248_coffeerun.jpg "More Obstacles!")

Here you can see the new trashcan and pillow obstacles!

![End Screen](https://cdn.discordapp.com/attachments/493900790004580363/518510251037949996/Screenshot_20181201-143341_coffeerun.jpg "End Screen!")

Here you can see the ending screen with a new background!

![Pause Screen](https://cdn.discordapp.com/attachments/493900790004580363/518510250538565633/Screenshot_20181201-143255_coffeerun.jpg "Pause Screen!")

Here you can see the pause screen with some new button textures!
