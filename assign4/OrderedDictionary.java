
public class OrderedDictionary implements OrderedDictionaryADT {
	private Node root;

	/**
	 * Create an Ordered Dictionary
	 **/
	public OrderedDictionary() {
		root = new Node();
	}

	/**
	 * Finds the record that contains pair k
	 * 
	 * @return the Record object with key k, or it returns null if such a record is
	 *         not in the dictionary.
	 **/
	public Record get(Pair k) {
		if (root.isLeaf()) {
			return root.getRecord();
		}
		Node n = root;
		while (n.getLeft() != null && n.getRight() != null && k.compareTo(n.getRecord().getKey()) != 0) {
			if (k.compareTo(n.getRecord().getKey()) == -1) {
				n = n.getLeft();
			} else {
				n = n.getRight();
			}
		}
		return n.getRecord();

	}

	/**
	 * Finds node with the Record r
	 * 
	 * @param Record
	 *            r
	 * @return node with the Record r
	 */
	private Node get(Record r) {
		if (root.isLeaf())
			return root;
		Node n = root;
		while (n.getLeft() != null && n.getRight() != null && r.getKey().compareTo(n.getRecord().getKey()) != 0) {
			if (r.getKey().compareTo(n.getRecord().getKey()) == -1) {
				n = n.getLeft();
			} else {
				n = n.getRight();
			}
		}
		return n;
	}

	/**
	 * Inserts r into the ordered dictionary. It throws a DictionaryException if a
	 * record with the same key as r is already in the dictionary.
	 * 
	 * @param Record
	 *            r to insert in a dictionary
	 **/
	public void put(Record r) throws DictionaryException {
		Node n = get(r);
		if (n.getLeft() != null && n.getRight() != null)
			throw new DictionaryException("Record with the same key is already in the dictionary!");
		n.setRecord(r);
		Node left = new Node();
		Node right = new Node();
		n.setLeft(left); // set children to be nodes with null objects
		n.setRight(right);
		left.setParent(n);
		right.setParent(n);
	}

	/**
	 * Removes the record with key k from the dictionary. It throws a
	 * DictionaryException if the record is not in the dictionary.
	 * 
	 * @param Pair
	 *            k to remove from the dictionary
	 **/
	public void remove(Pair k) throws DictionaryException {
		Record r = new Record(k, "");
		Node n = get(r);
		Node c;

		if (n.isLeaf())
			throw new DictionaryException("Record is not in the dictionary!");
		if (n.getLeft().isLeaf() || n.getRight().isLeaf()) {
			if (n.getLeft().isLeaf()) { // if one child is a leaf
				c = n.getRight();
			} else {
				c = n.getLeft();
			}
			Node n1 = n.getParent();
			if (n1 == null) {
				root = c;
			} else {
				if (n.getRecord() == n1.getLeft().getRecord()) {
					n1.setLeft(c);
				} else {
					n1.setRight(c);
				}
			}
		} else { // if node to remove has 2 children
			Node s = smallest(n.getRight());
			n.setRecord(s.getRecord());
			Node p1 = s.getParent();

			if (p1.getLeft() == s) {
				p1.setLeft(s.getRight());
			} else {
				p1.setRight(s.getRight());
			}

			s.getRight().setParent(p1);
		}
	}

	/**
	 * Finds successor of node with pair k, where successor is (the record from the
	 * ordered dictionary with smallest key larger than k); it returns null if the
	 * given key has no successor. The given key DOES NOT need to be in the
	 * dictionary.
	 * 
	 * @param Pair
	 *            k
	 * @return the successor of k or null
	 **/
	public Record successor(Pair k) {
		Record r = new Record(k, "");
		if (root.isLeaf())
			return null;
		Node p = get(r);
		if (p.getRight().isLeaf()) {
			Node p1 = p.getParent();
			while (p1 != null && p == p1.getRight()) {
				p = p1;
				p1 = p.getParent(); // go to parent nodes until successor is found or root is reached
			}
			if (p1 == null)
				return null;
			return p1.getRecord();
		}
		return smallest(p.getRight().getRecord());
	}

	/**
	 * Finds predecessor of k where predecessor is (the record from the ordered
	 * dictionary with largest key smaller than k; it returns null if the given key
	 * has no predecessor. The given key DOES NOT need to be in the dictionary.
	 * 
	 * @param Key
	 *            k
	 * @return predecessor of k or null
	 **/
	public Record predecessor(Pair k) {
		Record r = new Record(k, "");

		if (root.isLeaf()) {
			return null;
		} else {
			Node p = get(r);
			if (!(p.getLeft().isLeaf())) {
				return largest(p.getLeft().getRecord());
			} else {
				Node p1 = p.getParent();
				while (p1 != null && p == p1.getLeft()) {
					p = p1;
					p1 = p.getParent(); // go see if one of parents is predec
				}
				if (p1 == null)
					return null; // predec not found
				return p1.getRecord();
			}
		}
	}

	/**
	 * Finds the record with smallest key in the ordered dictionary
	 * 
	 * @return the record with smallest key in the ordered dictionary or null if the
	 *         dictionary is empty
	 **/
	public Record smallest() {
		if (root.isLeaf()) {
			return null;
		} else {
			Node p = root;
			while (p.getLeft() != null && p.getRight() != null) {
				p = p.getLeft();
			}
			return p.getParent().getRecord();
		}
	}

	/**
	 * Finds the node with smallest key in the ordered dictionary
	 * 
	 * @param node
	 *            to search from
	 * @return node with smallest key in the ordered dictionary or null if the
	 *         dictionary is empty
	 **/
	private Node smallest(Node rSubTree) {
		if (rSubTree.isLeaf()) {
			return null;
		} else {
			Node p = rSubTree;
			while (p.getLeft() != null && p.getRight() != null) {
				p = p.getLeft();
			}
			return p.getParent();
		}
	}

	/**
	 * Finds record with smallest key in the ordered dictionary
	 * 
	 * @param Record
	 *            r
	 * @return smallest record that exists in the subtree of node with Record r
	 */
	private Record smallest(Record r) {
		Node rSubTree = get(r);
		if (rSubTree.isLeaf()) {
			return null;
		} else {
			Node p = rSubTree;
			while (p.getLeft() != null && p.getRight() != null) {
				p = p.getLeft();
			}
			return p.getParent().getRecord();
		}
	}

	/**
	 * finds the record with largest key in the ordered dictionary. Returns null if
	 * the dictionary is empty.
	 * 
	 * @return record with largest key in the ordered dictionary or null if
	 *         dictionary is empty
	 **/
	public Record largest() {
		if (root.isLeaf()) {
			return null;
		} else {
			Node n = root;
			while (n.getLeft() != null && n.getRight() != null) {
				n = n.getRight();
			}
			return n.getParent().getRecord();
		}
	}

	/**
	 * finds the node with largest key in the ordered dictionary.
	 * 
	 * @param Record
	 *            r to find largest node from
	 * @return record with largest key in the subtree of Record r
	 */
	private Record largest(Record r) {
		Node rSubTree = get(r);
		if (rSubTree.isLeaf())
			return null;
		Node n = rSubTree;
		while (n.getLeft() != null && n.getRight() != null) {
			n = n.getRight();
		}
		return n.getParent().getRecord();
	}

}