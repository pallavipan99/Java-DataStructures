package forensic;

/**
 * This class represents a forensic analysis system that manages DNA data using
 * BSTs.
 * Contains methods to create, read, update, delete, and flag profiles.
 * 
 */
public class ForensicAnalysis {

    private TreeNode treeRoot; // BST's root
    private String firstUnknownSequence;
    private String secondUnknownSequence;

    public ForensicAnalysis() {
        treeRoot = null;
        firstUnknownSequence = null;
        secondUnknownSequence = null;
    }

    /**
     * Builds a simplified forensic analysis database as a BST and populates unknown
     * sequences.
     * The input file is formatted as follows:
     * 1. one line containing the number of people in the database, say p
     * 2. one line containing first unknown sequence
     * 3. one line containing second unknown sequence
     * 2. for each person (p), this method:
     * - reads the person's name
     * - calls buildSingleProfile to return a single profile.
     * - calls insertPerson on the profile built to insert into BST.
     * Use the BST insertion algorithm from class to insert.
     * 
     * DO NOT EDIT this method, IMPLEMENT buildSingleProfile and insertPerson.
     * 
     * @param filename the name of the file to read from
     */
    public void buildTree(String filename) {
        // DO NOT EDIT THIS CODE
        StdIn.setFile(filename); // DO NOT remove this line

        // Reads unknown sequences
        String sequence1 = StdIn.readLine();
        firstUnknownSequence = sequence1;
        String sequence2 = StdIn.readLine();
        secondUnknownSequence = sequence2;

        int numberOfPeople = Integer.parseInt(StdIn.readLine());

        for (int i = 0; i < numberOfPeople; i++) {
            // Reads name, count of STRs
            String fname = StdIn.readString();
            String lname = StdIn.readString();
            String fullName = lname + ", " + fname;
            // Calls buildSingleProfile to create
            Profile profileToAdd = createSingleProfile();
            // Calls insertPerson on that profile: inserts a key-value pair (name, profile)
            insertPerson(fullName, profileToAdd);
        }
    }

    /**
     * Reads ONE profile from input file and returns a new Profile.
     * Do not add a StdIn.setFile statement, that is done for you in buildTree.
     */
    public Profile createSingleProfile() {
        // WRITE YOUR CODE HERE
        STR[] numOfStrs = new STR[StdIn.readInt()];
        for (int i = 0; i < numOfStrs.length; i++) {
            numOfStrs[i] = new STR(StdIn.readString(), StdIn.readInt());
        }
        return new Profile(numOfStrs);
    }

    /**
     * Inserts a node with a new (key, value) pair into
     * the binary search tree rooted at treeRoot.
     * 
     * Names are the keys, Profiles are the values.
     * USE the compareTo method on keys.
     * 
     * @param newProfile the profile to be inserted
     */
    public void insertPerson(String name, Profile newProfile) {
        // WRITE YOUR CODE HERE
        TreeNode tree = new TreeNode();
        TreeNode node = new TreeNode(name, newProfile, null, null);
        tree = treeRoot;
        boolean isTrue = false;
        if (treeRoot == null) {
            treeRoot = node;
            tree = treeRoot;
        } else if (name.compareTo(treeRoot.getName()) < 0 && treeRoot.getLeft() == null) {
            treeRoot.setLeft(node);
        } else if (name.compareTo(treeRoot.getName()) > 0 && treeRoot.getRight() == null) {
            treeRoot.setRight(node);
        } else {
            while (tree != null && !isTrue) {
                if (node.getName().compareTo(tree.getName()) < 0) {
                    if (tree.getLeft() == null) {
                        tree.setLeft(node);
                        isTrue = true;
                    } else {
                        tree = tree.getLeft();
                    }
                } else if (node.getName().compareTo(tree.getName()) > 0) {
                    if (tree.getRight() == null) {
                        tree.setRight(node);
                        isTrue = true;
                    } else {
                        tree = tree.getRight();
                    }
                }
            }
        }
    }

    /**
     * Finds the number of profiles in the BST whose interest status matches
     * isOfInterest.
     *
     * @param isOfInterest the search mode: whether we are searching for unmarked or
     *                     marked profiles. true if yes, false otherwise
     * @return the number of profiles according to the search mode marked
     */
    public int getMatchingProfileCount(boolean isOfInterest) {
        // WRITE YOUR CODE HERE
        Queue<TreeNode> queue = new Queue<TreeNode>();
        int numOfMatchesOfProfiles = 0;
        queue.enqueue(treeRoot);
        while (!queue.isEmpty()) {
            if (queue.peek().getProfile() != null && queue.peek().getProfile().getMarkedStatus() == isOfInterest) {
                numOfMatchesOfProfiles++;
            }
            if (queue.peek().getLeft() != null) {
                queue.enqueue(queue.peek().getLeft());
            }
            if (queue.peek().getRight() != null) {
                queue.enqueue(queue.peek().getRight());
            }
            queue.dequeue();
        }
        return numOfMatchesOfProfiles; // update this line
    }

    /**
     * Helper method that counts the # of STR occurrences in a sequence.
     * Provided method - DO NOT UPDATE.
     * 
     * @param sequence the sequence to search
     * @param STR      the STR to count occurrences of
     * @return the number of times STR appears in sequence
     */
    private int numberOfOccurrences(String sequence, String STR) {

        // DO NOT EDIT THIS CODE

        int repeats = 0;
        // STRs can't be greater than a sequence
        if (STR.length() > sequence.length())
            return 0;

        // indexOf returns the first index of STR in sequence, -1 if not found
        int lastOccurrence = sequence.indexOf(STR);

        while (lastOccurrence != -1) {
            repeats++;
            // Move start index beyond the last found occurrence
            lastOccurrence = sequence.indexOf(STR, lastOccurrence + STR.length());
        }
        return repeats;
    }

    /**
     * Traverses the BST at treeRoot to mark profiles if:
     * - For each STR in profile STRs: at least half of STR occurrences match (round
     * UP)
     * - If occurrences THROUGHOUT DNA (first + second sequence combined) matches
     * occurrences, add a match
     */
    public void flagProfilesOfInterest() {
        // WRITE YOUR CODE HERE

        Queue<TreeNode> queue = new Queue<TreeNode>();
        int numOfMatches = 0;
        queue.enqueue(treeRoot);
        // System.out.println(firstUnknownSequence);
        // System.out.println(secondUnknownSequence);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.peek().getProfile().getStrs().length; i++) {
                if ((numberOfOccurrences(firstUnknownSequence, queue.peek().getProfile().getStrs()[i].getStrString())
                        + numberOfOccurrences(secondUnknownSequence,
                                queue.peek().getProfile().getStrs()[i].getStrString())) == queue.peek().getProfile()
                                        .getStrs()[i].getOccurrences()) {
                    numOfMatches++;
                }
            }
            if (numOfMatches >= (queue.peek().getProfile().getStrs().length + 1) / 2) {
                queue.peek().getProfile().setInterestStatus(true);
            }
            numOfMatches = 0;
            if (queue.peek().getLeft() != null) {
                queue.enqueue(queue.peek().getLeft());
            }
            if (queue.peek().getRight() != null) {
                queue.enqueue(queue.peek().getRight());
            }
            queue.dequeue();
        }
    }

    /**
     * Uses a level-order traversal to populate an array of unmarked Strings
     * representing unmarked people's names.
     * - USE the getMatchingProfileCount method to get the resulting array length.
     * - USE the provided Queue class to investigate a node and enqueue its
     * neighbors.
     * 
     * @return the array of unmarked people
     */
    public String[] getUnmarkedPeople() {
        // WRITE YOUR CODE HERE
        String[] unmarkedProfiles = new String[getMatchingProfileCount(false)];
        Queue<TreeNode> queue = new Queue<TreeNode>();
        queue.enqueue(treeRoot);
        while (!queue.isEmpty()) {
            if (queue.peek().getProfile() != null && !queue.peek().getProfile().getMarkedStatus()) {
                for (int i = 0; i < unmarkedProfiles.length; i++) {
                    if (unmarkedProfiles[i] == null) {
                        unmarkedProfiles[i] = queue.peek().getName();
                        break;
                    }
                }
            }
            if (queue.peek().getLeft() != null) {
                queue.enqueue(queue.peek().getLeft());
            }
            if (queue.peek().getRight() != null) {
                queue.enqueue(queue.peek().getRight());
            }
            queue.dequeue();
        }
        return unmarkedProfiles; // update this line
    }

    /**
     * Removes a SINGLE node from the BST rooted at treeRoot, given a full name
     * (Last, First)
     * This is similar to the BST delete we have seen in class.
     * 
     * If a profile containing fullName doesn't exist, do nothing.
     * You may assume that all names are distinct.
     * 
     * @param fullName the full name of the person to delete
     */
    public void removePerson(String fullName) {
        // WRITE YOUR CODE HERE
        Queue<TreeNode> queue = new Queue<TreeNode>();
        TreeNode current = new TreeNode();
        TreeNode prev = new TreeNode();
        queue.enqueue(treeRoot);
        while (!queue.isEmpty()) {
            prev = current;
            current = queue.dequeue();
            if (current.getName() != null && fullName.compareTo(current.getName()) > 0) {
                if (current.getRight() != null) {
                    queue.enqueue(current.getRight());
                } else if (current.getLeft() != null && current.getLeft().getName().equals(fullName)) {
                    queue.enqueue(current.getLeft());
                }
            } else if (fullName.compareTo(current.getName()) < 0) {
                if (current.getLeft() != null) {
                    queue.enqueue(current.getLeft());
                } else if (current.getRight() != null && current.getRight().getName().equals(fullName)) {
                    queue.enqueue(current.getRight());
                }
            } else if (fullName.compareTo(current.getName()) == 0) {
                if (current.getLeft() == null && current.getRight() == null) {
                    if (prev.getLeft() != null && prev.getLeft().getName().equals(fullName)) {
                        prev.setLeft(null);
                    } else if (prev.getRight() != null && prev.getRight().getName().equals(fullName)) {
                        prev.setRight(null);
                    } else {
                        treeRoot = null;

                    }
                } else if (current.getLeft() == null && current.getRight() != null) {
                    current.setName(current.getRight().getName());
                    current.setProfile(current.getRight().getProfile());
                    if (current.getRight().getLeft() != null) {
                        current.setRight(current.getRight().getLeft());
                    } else {
                        current.setRight(current.getRight().getRight());
                    }
                } else if (current.getLeft() != null && current.getRight() == null) {
                    current.setName(current.getLeft().getName());
                    current.setProfile(current.getLeft().getProfile());
                    if (current.getLeft().getLeft() != null) {
                        current.setLeft(current.getLeft().getLeft());
                    } else {
                        current.setLeft(current.getLeft().getRight());
                    }
                } else if (current.getLeft() != null && current.getRight() != null) { // need to fix this part
                    TreeNode min = new TreeNode();
                    TreeNode prev2 = new TreeNode();
                    min = current.getRight();
                    while (min.getLeft() != null) {
                        prev2 = min;
                        min = min.getLeft();
                    }
                    current.setName(min.getName());
                    current.setProfile(min.getProfile());
                    if (min.getRight() != null) {
                        prev2.setLeft(min.getRight());
                    } else if (current.getRight() != null && current.getRight().getRight() == null
                            && current.getRight().getLeft() == null) {
                        current.setRight(null);
                    } else if (min.getLeft() == null && min.getRight() == null) {
                        prev2.setLeft(null);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Clean up the tree by using previously written methods to remove unmarked
     * profiles.
     * Requires the use of getUnmarkedPeople and removePerson.
     */
    public void cleanupTree() {
        // WRITE YOUR CODE HERE
        String[] unMarkedPeople = getUnmarkedPeople();
        for (int i = 0; i < unMarkedPeople.length; i++) {
            removePerson(unMarkedPeople[i]);
        }
    }

    /**
     * Gets the root of the binary search tree.
     *
     * @return The root of the binary search tree.
     */
    public TreeNode getTreeRoot() {
        return treeRoot;
    }

    /**
     * Sets the root of the binary search tree.
     *
     * @param newRoot The new root of the binary search tree.
     */
    public void setTreeRoot(TreeNode newRoot) {
        treeRoot = newRoot;
    }

    /**
     * Gets the first unknown sequence.
     * 
     * @return the first unknown sequence.
     */
    public String getFirstUnknownSequence() {
        return firstUnknownSequence;
    }

    /**
     * Sets the first unknown sequence.
     * 
     * @param newFirst the value to set.
     */
    public void setFirstUnknownSequence(String newFirst) {
        firstUnknownSequence = newFirst;
    }

    /**
     * Gets the second unknown sequence.
     * 
     * @return the second unknown sequence.
     */
    public String getSecondUnknownSequence() {
        return secondUnknownSequence;
    }

    /**
     * Sets the second unknown sequence.
     * 
     * @param newSecond the value to set.
     */
    public void setSecondUnknownSequence(String newSecond) {
        secondUnknownSequence = newSecond;
    }

}
