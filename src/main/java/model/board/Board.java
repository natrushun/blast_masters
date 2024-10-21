package model.board;

import model.board.element.Empty;
import model.board.element.Entity;
import model.board.element.character.*;
import model.board.element.deposable.Bomb;
import model.board.element.deposable.Box;
import model.board.element.deposable.Flame;
import model.board.element.field.Wall;
import model.board.element.powerup.Bonus;
import model.board.element.powerup.benefit.*;
import model.board.element.powerup.handicap.*;
import view.state.GameState;
import view.ui.PlayerDataPanel;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static model.board.Image.*;
import static model.board.Size.*;
import static model.board.Velocity.*;
import static view.state.GameState.*;

/**
 * Represents the game board, containing various elements such as players, monsters, walls, boxes, bonuses, and bombs.
 */
public class Board {

    public int selectedMapIndex;
    private final int boardSize;
    private int numberOfRound;
    private boolean onlyOneAlive;
    private boolean player1Check;
    private boolean player2Check;
    private String path;
    private Timer afterDeathTimer;
    private GameState state;
    private Player player1;
    private Player player2;
    private ArrayList<Entity> boardElements;
    private ArrayList<Monster> monsters;
    private ArrayList<Wall> walls;
    private ArrayList<Box> boxes;
    private ArrayList<Bonus> bonuses;
    private ArrayList<Bomb> bombs;
    private Entity[][] staticElements;

    /**
     * Constructs a game board with the specified size, path to the map file, and selected map index.
     *
     * @param boardSize        the size of the game board
     * @param path             the path to the map file
     * @param selectedMapIndex the index of the selected map
     * @throws IOException if an I/O error occurs
     */
    public Board(int boardSize, String path, int selectedMapIndex, int numberOfRound) throws IOException {
        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        boxes = new ArrayList<>();
        bonuses = new ArrayList<>();
        bombs = new ArrayList<>();
        this.boardSize = boardSize;
        this.selectedMapIndex = selectedMapIndex;
        this.path = path;
        this.numberOfRound = numberOfRound;
        onlyOneAlive = false;
        player1Check = false;
        player2Check = false;
        state = BOTH_ALIVE;
        afterDeathTimer = new javax.swing.Timer(3 * 1000, new deathTimer());
        afterDeathTimer.setRepeats(false);
        initialize(path, selectedMapIndex);
        putBonusesInBoxes();
    }

    /**
     * Initializes the game board based on the map file.
     *
     * @param path             the path to the map file
     * @param selectedMapIndex the index of the selected map
     */
    public void initialize(String path, int selectedMapIndex) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            boardElements = new ArrayList<>();
            staticElements = new Entity[BOARD_HEIGHT.getSize()][BOARD_WIDTH.getSize()];
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                int col = 0;
                for (char entityType : line.toCharArray()) {
                    int x = col * TILE_WIDTH.getSize();
                    int y = row * TILE_HEIGHT.getSize();//+TILE_HEIGHT.getSize();
                    switch (entityType) {
                        case 'W':
                            Wall wall = new Wall(x, y, WALL_SIZE.getSize(), WALL_SIZE.getSize(), WALL_VEL.getVelocity(),
                                    getWallImage(selectedMapIndex).getImage(), false, true);
                            boardElements.add(wall);
                            walls.add(wall);
                            staticElements[row][col] = wall;
                            break;
                        case 'E':
                            Empty empty = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            staticElements[row][col] = empty;
                            break;
                        case 'B':
                            Box box = new Box(x, y, BOX_SIZE.getSize(), BOX_SIZE.getSize(), BOX_VEL.getVelocity(),
                                    getBoxImage(selectedMapIndex).getImage(), false, true, null, this);
                            boardElements.add(box);
                            boxes.add(box);
                            staticElements[row][col] = box;
                            break;
                        case 'M':
                            BasicMonster basicMonster = new BasicMonster(x, y, MONSTER_SIZE.getSize(), MONSTER_SIZE.getSize(),
                                    MONSTER_VEL.getVelocity(), getMonsterImage(selectedMapIndex), true, true, this);
                            boardElements.add(basicMonster);
                            monsters.add(basicMonster);
                            staticElements[row][col] = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            break;
                        case 'G':
                            List<String> ghostMonsterImageUrls = Image.GHOST_MONSTER_IMG.getImageUrls();
                            List<java.awt.Image> ghostMonsterImages = new ArrayList<>();

                            for (String url : ghostMonsterImageUrls) {
                                ghostMonsterImages.add(new ImageIcon(url).getImage());
                            }

                            GhostMonster ghostMonster = new GhostMonster(x, y, MONSTER_SIZE.getSize(), MONSTER_SIZE.getSize(),
                                    GHOST_MONSTER_VEL.getVelocity(), ghostMonsterImages, true, true, this);
                            boardElements.add(ghostMonster);
                            monsters.add(ghostMonster);
                            staticElements[row][col] = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            break;

                        case 'S':
                            List<String> semiIntelligentMonsterImageUrls = Image.SEMI_INTELLIGENT_MONSTER_IMG.getImageUrls();
                            List<java.awt.Image> semiIntelligentMonsterImages = new ArrayList<>();

                            for (String url : semiIntelligentMonsterImageUrls) {
                                semiIntelligentMonsterImages.add(new ImageIcon(url).getImage());
                            }

                            SemiIntelligentMonster semiIntelligentMonster = new SemiIntelligentMonster(x, y, MONSTER_SIZE.getSize(), MONSTER_SIZE.getSize(),
                                    SEMI_INTELLIGENT_MONSTER_VEL.getVelocity(), semiIntelligentMonsterImages, true, true, this);
                            boardElements.add(semiIntelligentMonster);
                            monsters.add(semiIntelligentMonster);
                            staticElements[row][col] = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            break;
                        case 'I':
                            List<String> intelligentMonsterImgImageUrls = Image.INTELLIGENT_MONSTER_IMG.getImageUrls();
                            List<java.awt.Image> intelligentMonsterImages = new ArrayList<>();

                            for (String url : intelligentMonsterImgImageUrls) {
                                intelligentMonsterImages.add(new ImageIcon(url).getImage());
                            }

                            IntelligentMonster intelligentMonster = new IntelligentMonster(x, y, INTELLIGENTMONSTER_WIDTH.getSize(), INTELLIGENTMONSTER_HEIGHT.getSize(),
                                    INTELLIGENT_MONSTER_VEL.getVelocity(), intelligentMonsterImages, true, true, this);
                            boardElements.add(intelligentMonster);
                            monsters.add(intelligentMonster);
                            staticElements[row][col] = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            break;
                        case '1':
                            List<String> player1ImageUrls = Image.PLAYER1_IMG.getImageUrls();
                            List<java.awt.Image> player1Images = new ArrayList<>();
                            List<String> player1ImmortalImgUrls = PLAYER1_IMMORTAL_IMG.getImageUrls();
                            List<java.awt.Image> player1ImmortalImages = new ArrayList<>();
                            for (String url : player1ImageUrls) {
                                player1Images.add(new ImageIcon(url).getImage());
                            }
                            for (String url : player1ImmortalImgUrls) {
                                player1ImmortalImages.add(new ImageIcon(url).getImage());
                            }
                            player1 = new Player(x, y, PLAYER_WIDTH.getSize(), PLAYER_HEIGHT.getSize(), PLAYER_VEL.getVelocity(),
                                    player1Images, player1ImmortalImages, true, true, "Player1", this);
                            boardElements.add(player1);
                            staticElements[row][col] = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            break;
                        case '2':
                            List<String> player2ImageUrls = Image.PLAYER2_IMG.getImageUrls();
                            List<String> player2ImmortalImgUrls = PLAYER2_IMMORTAL_IMG.getImageUrls();
                            List<java.awt.Image> player2Images = new ArrayList<>();
                            List<java.awt.Image> player2ImmortalImages = new ArrayList<>();
                            for (String url : player2ImageUrls) {
                                player2Images.add(new ImageIcon(url).getImage());
                            }
                            for (String url : player2ImmortalImgUrls) {
                                player2ImmortalImages.add(new ImageIcon(url).getImage());
                            }
                            player2 = new Player(x, y, PLAYER_WIDTH.getSize(), PLAYER_HEIGHT.getSize(), PLAYER_VEL.getVelocity(),
                                    player2Images, player2ImmortalImages, true, true, "Player2", this);
                            boardElements.add(player2);
                            staticElements[row][col] = new Empty(x, y, TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                            break;
                    }
                    col++;
                }
                row++;
            }
            br.close();
        } catch (IOException e) {
            System.err.println("File not found");
        }
    }

    public ImageIcon getWallImage(int mapIndex) {
        return switch (mapIndex) {
            case 1 -> new ImageIcon(WALL_IMG_MAP2.getImageUrl());
            case 2 -> new ImageIcon(WALL_IMG_MAP3.getImageUrl());
            default -> new ImageIcon(WALL_IMG_MAP1.getImageUrl());
        };
    }
    private ImageIcon getBoxImage(int mapIndex) {
        return switch (mapIndex) {
            case 1 -> new ImageIcon(BOX_IMG_MAP2.getImageUrl());
            case 2 -> new ImageIcon(BOX_IMG_MAP3.getImageUrl());
            default -> new ImageIcon(BOX_IMG_MAP1.getImageUrl());
        };
    }

    private List<java.awt.Image> getMonsterImage(int mapIndex) {
        return switch (mapIndex) {
            case 1 -> {
                List<String> monster2ImageUrls = Image.MONSTER_IMG_MAP2.getImageUrls();
                List<java.awt.Image> monster2Images = new ArrayList<>();
                for (String url : monster2ImageUrls) {
                    monster2Images.add(new ImageIcon(url).getImage());
                }
                yield monster2Images;
            }
            case 2 -> {
                List<String> monster3ImageUrls = Image.MONSTER_IMG_MAP3.getImageUrls();
                List<java.awt.Image> monster3Images = new ArrayList<>();
                for (String url : monster3ImageUrls) {
                    monster3Images.add(new ImageIcon(url).getImage());
                }
                yield monster3Images;
            }
            default -> {
                List<String> monster1ImageUrls = Image.MONSTER_IMG_MAP1.getImageUrls();
                List<java.awt.Image> monster1Images = new ArrayList<>();
                for (String url : monster1ImageUrls) {
                    monster1Images.add(new ImageIcon(url).getImage());
                }
                yield monster1Images;
            }
        };
    }
    public int getSelectedMapIndex() {
        return selectedMapIndex;
    }
    public void moveMonsters() {
        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                monster.move();
            }
        }
    }
    public void player1PlantsBomb() {
        this.player1.plantBomb();
    }
    public void player1PlantsBox() {
        this.player1.plantBox();
    }
    public void player2PlantsBomb() {
        this.player2.plantBomb();
    }

    public void player2PlantsBox() {
        this.player2.plantBox();
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }

    public ArrayList<Monster> getMonsters() {
        return new ArrayList<Monster>(this.monsters);
    }

    public ArrayList<Entity> getEntities() {
        return boardElements;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public void addEntity(Entity entity) {
        boardElements.add(entity);
    }

    public void putBonusesInBoxes() {
        int numberOfBonuses = boxes.size() / 2;
        Collections.shuffle(boxes);
        ArrayList<Box> boxesWithBonuses = new ArrayList<>(boxes.subList(0, numberOfBonuses));
        for (Box box : boxesWithBonuses) {
            putRandomBonusInBox(box);
        }
    }

    /**
     * Randomly assigns a bonus to the provided box. The bonus type is determined randomly from a predefined set of bonuses.
     * Once the bonus is assigned to the box, it is added to the list of board elements and the list of bonuses.
     *
     * @param box The box to which the bonus will be assigned.
     */
    public void putRandomBonusInBox(Box box) {
        Random random = new Random();
        int randomNumber = random.nextInt(11);
        Bonus bonus = null;
        switch (randomNumber) {
            case 0:
                bonus = new BiggerRangeBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(BIGGER_RANGE_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 1:
                bonus = new MaxBombsBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(BOMB_UP_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 2:
                bonus = new RollerBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(ROLLER_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 3:
                bonus = new SlowDownBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(SLOW_DOWN_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 4:
                bonus = new DetonatorBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(DETONATOR_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 5:
                bonus = new PlaceBombsImmediatelyBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(IMMEDIATELY_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 6:
                bonus = new NoBombsBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(PACIFIST_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 7:
                bonus = new SmallerRangeBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(SMALLERRANGE_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 8:
                bonus = new GhostBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(GHOST_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 9:
                bonus = new BoxBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(BOX_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
            case 10:
                bonus = new ImmortalityBonus(box.getX(), box.getY(), BONUS_SIZE.getSize(), BONUS_SIZE.getSize(), BONUS_VEL.getVelocity(), new ImageIcon(IMMORTALITY_BONUS_BUBBLE.getImageUrl()).getImage(), false, false, null);
                break;
        }
        box.setBonus(bonus);
        boardElements.add(bonus);
        bonuses.add(bonus);
    }

    /**
     * Removes entities marked as removable from the game board.
     */
    public void removeRemovableEntities() {
        ArrayList<Entity> removableElements = new ArrayList<>();
        ArrayList<Monster> removableMonsters = new ArrayList<>();
        ArrayList<Entity> elements = new ArrayList<>(boardElements);
        ArrayList<Monster> monsters2 = new ArrayList<>(monsters);
        for (Entity entity : elements) {
            if (entity.isRemovable()) {
                removableElements.add(entity);
                if (entity instanceof Box || entity instanceof Bomb || entity instanceof Player) {
                    staticElements[entity.getRow()][entity.getColumn()] = new Empty(entity.getX(), entity.getY(), TILE_WIDTH.getSize(), TILE_HEIGHT.getSize());
                }
            }

        }
        for (Monster monster : monsters2) {
            if (monster.isRemovable()) removableMonsters.add(monster);
        }
        boardElements.removeAll(removableElements);
        monsters.removeAll(removableMonsters);
    }

    /**
     * Checks the current status of the game, updating the state accordingly.
     */
    public void statusCheck() {
        if (state == BOTH_ALIVE) {
            if (!player1.isAlive()) {
                if (!onlyOneAlive) {
                    player2Check = true;
                    onlyOneAlive = true;
                    afterDeathTimer.start();
                }
            }
            if (!player2.isAlive()) {
                if (!onlyOneAlive) {
                    player1Check = true;
                    onlyOneAlive = true;
                    afterDeathTimer.start();
                }
            }
        }
    }

    /**
     * Represents an ActionListener for the after death timer.
     */
    class deathTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            stopBombs();
            if (player1Check) {
                if (!player1.isAlive()) {
                    state = DRAW;
                } else {
                    player1.incrementPoints();
                    if (player1.getPoints() > numberOfRound / 2) {
                        state = PLAYER1_FINAL_WIN;
                    } else {
                        state = PLAYER1_WON;
                    }

                }
            }
            if (player2Check) {
                if (!player2.isAlive()) {
                    state = DRAW;
                } else {
                    player2.incrementPoints();
                    if (player2.getPoints() > numberOfRound / 2) {
                        state = PLAYER2_FINAL_WIN;
                    } else {
                        state = PLAYER2_WON;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Board Information:\n");
        sb.append("Size: ").append(boardSize).append("\n");

        // Players
        sb.append("Player 1: ").append(player1.getName()).append("\n");
        sb.append("Player 2: ").append(player2.getName()).append("\n");

        // Monsters
        sb.append("Number of Monsters: ").append(monsters.size()).append("\n");

        // Board Elements
        sb.append("Number of Board Elements: ").append(boardElements.size()).append("\n");
        sb.append("Walls: ").append(walls.size()).append("\n");
        sb.append("Boxes: ").append(boxes.size()).append("\n");
        sb.append("Bonuses: ").append(bonuses.size()).append("\n");
        sb.append("Bombs: ").append(bombs.size()).append("\n");

        return sb.toString();
    }

    public GameState getGameState() {
        return state;
    }

    /**
     * Resets the game board to its initial state, including resetting elements and scores.
     */
    public void reset(boolean newNewRound) {
        state = BOTH_ALIVE;
        monsters = new ArrayList<>();
        walls = new ArrayList<>();
        boxes = new ArrayList<>();
        bonuses = new ArrayList<>();
        bombs = new ArrayList<>();
        onlyOneAlive = false;
        player1Check = false;
        player2Check = false;
        afterDeathTimer = new javax.swing.Timer(3 * 1000, new deathTimer());
        afterDeathTimer.setRepeats(false);
        int tempPlayer1Points;
        int tempPlayer2Points;
        if (newNewRound) {
            tempPlayer1Points = 0;
            tempPlayer2Points = 0;
        } else {
            tempPlayer1Points = player1.getPoints();
            tempPlayer2Points = player2.getPoints();
        }
        initialize(path, selectedMapIndex);
        putBonusesInBoxes();
        player1.setPoints(tempPlayer1Points);
        player2.setPoints(tempPlayer2Points);
    }


    public Entity[][] getStaticElements() {
        return staticElements;
    }

    public void addStaticElement(Entity entity, int row, int col) {
        staticElements[row][col] = entity;
    }
    public void setPlayer1DataPanel(PlayerDataPanel panel){
        player1.setPlayerDataPanel(panel);
    }
    public void setPlayer2DataPanel(PlayerDataPanel panel){
        player2.setPlayerDataPanel(panel);
    }

    private void stopBombs(){
        for (Bomb bomb: bombs) {
            bomb.stop();
        }
    }
}
