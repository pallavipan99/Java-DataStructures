package climate;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered
 * linked list structure that contains USA communitie's Climate and Economic
 * information.
 * 
 */

public class ClimateEconJustice {

    private StateNode firstState;

    /*
     * Constructor
     * 
     * **** DO NOT EDIT *****
     */
    public ClimateEconJustice() {
        firstState = null;
    }

    /*
     * Get method to retrieve instance variable firstState
     * 
     * @return firstState
     * 
     * **** DO NOT EDIT *****
     */
    public StateNode getFirstState() {
        // DO NOT EDIT THIS CODE
        return firstState;
    }

    /**
     * Creates 3-layered linked structure consisting of state, county,
     * and community objects by reading in CSV file provided.
     * 
     * @param inputFile, the file read from the Driver to be used for
     * @return void
     * 
     *         **** DO NOT EDIT *****
     */
    public void createLinkedStructure(String inputFile) {

        // DO NOT EDIT THIS CODE
        StdIn.setFile(inputFile);
        StdIn.readLine(); // Skips header

        // Reads the file one line at a time
        while (StdIn.hasNextLine()) {
            // Reads a single line from input file
            String line = StdIn.readLine();
            // IMPLEMENT these methods
            addToStateLevel(line);
            addToCountyLevel(line);
            addToCommunityLevel(line);
        }
    }

    /*
     * Adds a state to the first level of the linked structure.
     * Do nothing if the state is already present in the structure.
     * 
     * @param inputLine a line from the input file
     */
    public void addToStateLevel(String inputLine) {
        // WRITE YOUR CODE HERE
        String[] info = inputLine.split(",");
        StateNode current = firstState;
        StateNode state = new StateNode(info[2], null, null);
        if (current == null) {
            firstState = state;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            if (!(current.getName().equals(info[2]))) {
                current.setNext(state);
            }
        }
    }

    /*
     * Adds a county to a state's list of counties.
     * 
     *
     * Access the state's list of counties' using the down pointer from the State
     * class.
     * Do nothing if the county is already present in the structure.
     * 
     * @param inputFile a line from the input file
     */
    public void addToCountyLevel(String inputLine) {
        // WRITE YOUR CODE HERE
        String[] info = inputLine.split(",");
        StateNode states = firstState;
        CountyNode connectCounties = states.getDown();
        CountyNode county = new CountyNode(info[1], null, null);
        while (states.getNext() != null) {
            states = states.getNext();
        }
        if (states.getName().equals(info[2])) {
            connectCounties = states.getDown();
        }
        if (connectCounties == null) {
            connectCounties = county;
            states.setDown(connectCounties);
        } else {
            while (connectCounties.getNext() != null) {
                connectCounties = connectCounties.getNext();
            }
            if (!(connectCounties.getName().equals(info[1]))) {
                connectCounties.setNext(county);
            }
        }

    }

    /*
     * Adds a community to a county's list of communities.
     * 
     * Access the county through its state
     * - search for the state first,
     * - then search for the county.
     * Use the state name and the county name from the inputLine to search.
     * 
     * Access the state's list of counties using the down pointer from the StateNode
     * class.
     * Access the county's list of communities using the down pointer from the
     * CountyNode class.
     * Do nothing if the community is already present in the structure.
     * 
     * @param inputFile a line from the input file
     */
    public void addToCommunityLevel(String inputLine) {
        // WRITE YOUR CODE HERE
        String[] info = inputLine.split(",");
        StateNode state = firstState;
        CountyNode county = state.getDown();
        CommunityNode community = county.getDown();
        Data data = new Data(Double.parseDouble(info[3]), Double.parseDouble(info[4]), Double.parseDouble(info[5]),
                Double.parseDouble(info[8]), Double.parseDouble(info[9]), info[19], Double.parseDouble(info[49]),
                Double.parseDouble(info[37]), Double.parseDouble(info[121]));
        CommunityNode addCommunity = new CommunityNode(info[0], null, data);
        while (state.getNext() != null) {
            state = state.getNext();
        }
        if (state.getName().equals(info[2])) {
            county = state.getDown();
        }

        while (county.getNext() != null) {
            county = county.getNext();
        }
        if (county.getName().equals(info[1])) {
            community = county.getDown();
        }

        if (community == null) {
            community = addCommunity;
            county.setDown(community);
        } else {
            while (community.getNext() != null) {
                community = community.getNext();
            }
            if (!(community.getName().equals(info[1]))) {
                community.setNext(addCommunity);
            }
        }
    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial
     * group
     * and are identified as disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial
     *                     groups
     * @param race         the race which will be returned
     * @return the amount of communities that contain the same or higher percentage
     *         of the given race
     */
    public int disadvantagedCommunities(double userPrcntage, String race) {
        // WRITE YOUR CODE HERE
        StateNode state = firstState;
        CountyNode county = null;// state.getDown();
        CommunityNode community = null; // county.getDown();
        int num = 0;
        while (state != null) {
            county = state.getDown();
            while (county != null) {
                community = county.getDown();
                while (community != null) {
                    if (community.getInfo().getAdvantageStatus().equals("True")) {
                        if (race.equals("African American")) {
                            if ((100 * community.getInfo().getPrcntAfricanAmerican()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("Native American")) {
                            if ((100 * community.getInfo().getPrcntNative()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("Asian American")) {
                            if ((100 * community.getInfo().getPrcntAsian()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("White American")) {
                            if ((100 * community.getInfo().getPrcntWhite()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("Hispanic American")) {
                            if ((100 * community.getInfo().getPrcntHispanic()) >= userPrcntage) {
                                num++;
                            }
                        }
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }
        return num; // update this line
    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial
     * group
     * and are identified as non disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial
     *                     groups
     * @param race         the race which will be returned
     * @return the amount of communities that contain the same or higher percentage
     *         of the given race
     */
    public int nonDisadvantagedCommunities(double userPrcntage, String race) {
        // WRITE YOUR CODE HERE
        StateNode state = firstState;
        CountyNode county = null;// state.getDown();
        CommunityNode community = null; // county.getDown();
        int num = 0;
        while (state != null) {
            county = state.getDown();
            while (county != null) {
                community = county.getDown();
                while (community != null) {
                    if (community.getInfo().getAdvantageStatus().equals("False")) {
                        if (race.equals("African American")) {
                            if ((100 * community.getInfo().getPrcntAfricanAmerican()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("Native American")) {
                            if ((100 * community.getInfo().getPrcntNative()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("Asian American")) {
                            if ((100 * community.getInfo().getPrcntAsian()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("White American")) {
                            if ((100 * community.getInfo().getPrcntWhite()) >= userPrcntage) {
                                num++;
                            }
                        } else if (race.equals("Hispanic American")) {
                            if ((100 * community.getInfo().getPrcntHispanic()) >= userPrcntage) {
                                num++;
                            }
                        }
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }
        return num; // update this line
    }

    /**
     * Returns a list of states that have a PM (particulate matter) level
     * equal to or higher than value inputted by user.
     * 
     * @param PMlevel the level of particulate matter
     * @return the States which have or exceed that level
     */
    public ArrayList<StateNode> statesPMLevels(double PMlevel) {
        // WRITE YOUR METHOD HERE
        StateNode state = firstState;
        CountyNode county = null;
        CommunityNode community = null;
        ArrayList<StateNode> states = new ArrayList<StateNode>();
        int count = 0;
        while (state != null) {
            county = state.getDown();
            while (county != null) {
                community = county.getDown();
                while (community != null) {
                    if (community.getInfo().getPMlevel() >= PMlevel) {
                        count++;
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            if (count >= 1) {
                states.add(state);
                count = 0;
            }
            state = state.getNext();
        }
        return states; // update this line
    }

    /**
     * Given a percentage inputted by user, returns the number of communities
     * that have a chance equal to or higher than said percentage of
     * experiencing a flood in the next 30 years.
     * 
     * @param userPercntage the percentage of interest/comparison
     * @return the amount of communities at risk of flooding
     */
    public int chanceOfFlood(double userPercntage) {
        // WRITE YOUR METHOD HERE
        StateNode state = firstState;
        CountyNode county = null;
        CommunityNode community = null;
        int num = 0;
        while (state != null) {
            county = state.getDown();
            while (county != null) {
                community = county.getDown();
                while (community != null) {
                    if (community.getInfo().getChanceOfFlood() >= userPercntage) {
                        num++;
                    }
                    community = community.getNext();
                }
                county = county.getNext();
            }
            state = state.getNext();
        }
        return num; // update this line
    }

    /**
     * Given a state inputted by user, returns the communities with
     * the 10 lowest incomes within said state.
     * 
     * @param stateName the State to be analyzed
     * @return the top 10 lowest income communities in the State, with no particular
     *         order
     */
    public ArrayList<CommunityNode> lowestIncomeCommunities(String stateName) {
        // WRITE YOUR METHOD HERE
        StateNode state = firstState;
        CountyNode county = state.getDown();
        CommunityNode community = county.getDown();
        ArrayList<CommunityNode> listOfCommunities = new ArrayList<CommunityNode>();
        ArrayList<Double> leastNumbers = new ArrayList<Double>();
        double min = Double.MAX_VALUE;
        int index = 0;
        while (state != null) {
            if (state.getName().equals(stateName)) {
                county = state.getDown();
                while (county != null) {
                    community = county.getDown();
                    while (community != null) {
                        // if (!(leastNumbers.contains(community.getInfo().getPercentPovertyLine()))) {
                        if (leastNumbers.size() == 10) {
                            // min = leastNumbers.get(0);
                            for (int i = 0; i < leastNumbers.size(); i++) {
                                if (leastNumbers.get(i) < min) {
                                    min = leastNumbers.get(i);
                                    index = i;
                                }
                            }
                            if (community.getInfo().getPercentPovertyLine() > min) {
                                leastNumbers.set(index, community.getInfo().getPercentPovertyLine());
                                listOfCommunities.set(index, community);
                            }
                            min = Double.MAX_VALUE;
                        } else {
                            leastNumbers.add(community.getInfo().getPercentPovertyLine());
                            listOfCommunities.add(community);
                        }
                        // }
                        community = community.getNext();
                    }
                    county = county.getNext();
                }
            }
            state = state.getNext();
        }
        return listOfCommunities; // update this line
    }
}
