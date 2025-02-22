package org.toysjourney.utilz;

import org.toysjourney.Game;

public class Constants {

    //FILM
    public static final int ANI_SPEED = 25;

    // FILM: only potion
    public static class ObjectConstants {
        public static final int RED_POTION = 0;
        public static final int BLUE_POTION = 1;
        public static final int RED_POTION_VALUE = 15;
        public static final int BLUE_POTION_VALUE = 10;
        public static final int POTION_WIDTH_DEFAULT = 12;
        public static final int POTION_HEIGHT_DEFAULT = 16;
        public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
        public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

        public static final int KEY_1 = 3;
        public static final int KEY_WIDTH_DEFAULT = (int) (50 * 0.76);
        public static final int KEY_HEIGHT_DEFAULT = (int) (50 * 0.76);
        public static final int KEY_WIDTH = (int) (Game.SCALE * KEY_WIDTH_DEFAULT);
        public static final int KEY_HEIGHT = (int) (Game.SCALE * KEY_HEIGHT_DEFAULT);

        public static int GetSpriteAmount(int object_type) {
            return switch (object_type) {
                case RED_POTION, BLUE_POTION -> 7;
                case KEY_1 -> 8;
                default -> 1;
            };

        }
    }

    public static class EnemyConstants {
        public static final int CRABBY = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

        public static int getSpriteAmount(int enemy_type, int enemy_state) {

            if (enemy_type == CRABBY) {
                switch (enemy_state) {
                    case IDLE:
                        return 9;
                    case RUNNING:
                        return 6;
                    case ATTACK:
                        return 7;
                    case HIT:
                        return 4;
                    case DEAD:
                        return 5;
                }
            }

            return 0;

        }

    }

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 109; //140
            public static final int B_HEIGHT_DEFAULT = 59; //56
            public static final int B_WIDTH = B_WIDTH_DEFAULT * 2;
            public static final int B_HEIGHT = B_HEIGHT_DEFAULT * 2; //112
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
        }

        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);

        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int DOWN = 1;
        public static final int RIGHT = 2;
        public static final int UP = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE_FRONT = 0;
        public static final int IDLE_RIGHT = 1;
        public static final int IDLE_LEFT = 2;
        public static final int IDLE_BACK = 3;
        public static final int RUN_FRONT = 4;
        public static final int RUN_RIGHT = 5;
        public static final int RUN_LEFT = 6;
        public static final int RUN_BACK = 7;
        public static final int ATK_RIGHT = 8;
        public static final int ATK_LEFT = 9;

        public static int GetSpriteAmount(int player_action) {

            return switch (player_action) {
                case IDLE_FRONT -> 5;
                case IDLE_RIGHT -> 5;
                case IDLE_LEFT -> 5;
                case IDLE_BACK -> 5;
                case RUN_FRONT -> 6;
                case RUN_RIGHT -> 6;
                case RUN_LEFT -> 6;
                case RUN_BACK -> 6;
                case ATK_RIGHT -> 8;
                case ATK_LEFT -> 8;
                default -> 0;
            };

        }

    }

}

















