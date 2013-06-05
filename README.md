Marathon Trainer
================

This program is a Java Swing program meant to provide a visual aid for your
marathon training.

Given a training plan and a marathon date, this program will provide you with
a calendar view of when you'll be training for what. Specifically, once you
enter your training plan and launch the application, it'll tell you (for
example) that today you'll be running 9 KM at a regular pace. (The paces for
the different run types are defined via the training plan.)

Setup
-----

All your different training plans should be placed into the `training-plan`
directory.

Files within the `training-plan` directory should be structured as such:

    # [run-type] [length or range]
    tempo 5:30
    speed 4:00-4:30
    regular 6:30

    # sunday | monday  | tuesday | wednesday | thursday    | friday | saturday
    tempo 3  | hills 1 |   rest  | regular 9 | speed 2.6 2 | long 2 | rest
    tempo 3  | hills 1 |   rest  | regular 9 | speed 2.6 3 | long 2 | rest
    tempo 3  | hills 1 |   rest  | regular 9 | speed 2.6 4 | long 2 | rest
    tempo 3  | hills 1 |   rest  | regular 9 | speed 3.2 5 | long 2 | rest

(Lines beginning with # are comments and should NOT be included in the file.)

Acceptable run types are the following:

  * Tempo
  * Regular
  * Challenge
  * Speed
  * Hills
  * Fartlek
  * Long
  * Rest

Note that the first part of the file should be the run type followed by a time
(minute:seconds) or a time range (minutes:seconds-minutes:seconds). Make sure
there are no spaces, and add a blank line in between the calibration times and
the training plan. All distances are in kilometers.
