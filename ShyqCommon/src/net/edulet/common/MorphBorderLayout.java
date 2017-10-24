/*
 * @(#)MorphBorderLayout.java	
 *
 */
package net.edulet.common;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

/**
 * A morph border layout lays out a container, arranging and resizing
 * its components to fit in five regions:
 * north, south, east, west, and center.
 * MorphBorderLayout extends from java.awt.BorderLayout, The CENTER part is fixed size,
 * while EAST and WEST parts are variable size according to the whole size.
 * Each region is identified by a corresponding constant:
 * <code>NORTH</code>, <code>SOUTH</code>, <code>EAST</code>,
 * <code>WEST</code>, and <code>CENTER</code>.  When adding a
 * component to a container with a border layout, use one of these
 * five constants.
 *
 * @version 
 * @author 	York Shao
 */
public class MorphBorderLayout extends java.awt.BorderLayout{
    /**
     * Constant to specify components location to be the
     *      north portion of the border layout.
     * @serial
     * @see #getChild
     * @see #addLayoutComponent
     * @see #getLayoutAllignment
     * @see #removeLayoutComponent
     */
	Component north;
     /**
     * Constant to specify components location to be the
     *      west portion of the border layout.
     * @serial
     * @see #getChild
     * @see #addLayoutComponent
     * @see #getLayoutAllignment
     * @see #removeLayoutComponent
     */
	Component west;
    /**
     * Constant to specify components location to be the
     *      east portion of the border layout.
     * @serial
     * @see #getChild
     * @see #addLayoutComponent
     * @see #getLayoutAllignment
     * @see #removeLayoutComponent
     */
	Component east;
    /**
     * Constant to specify components location to be the
     *      south portion of the border layout.
     * @serial
     * @see #getChild
     * @see #addLayoutComponent
     * @see #getLayoutAllignment
     * @see #removeLayoutComponent
     */
    Component south;
    /**
     * Constant to specify components location to be the
     *      center portion of the border layout.
     * @serial
     * @see #getChild
     * @see #addLayoutComponent
     * @see #getLayoutAllignment
     * @see #removeLayoutComponent
     */
	Component center;
    
    /**
     *
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * mixing the two types of constants can lead to unpredicable results.  If
     * you use both types, the relative constants will take precedence.
     * For example, if you add components using both the <code>NORTH</code>
     * and <code>BEFORE_FIRST_LINE</code> constants in a container whose
     * orientation is <code>LEFT_TO_RIGHT</code>, only the
     * <code>BEFORE_FIRST_LINE</code> will be layed out.
     * This will be the same for lastLine, firstItem, lastItem.
     * @serial
     */
    Component firstLine;
     /**
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * Please read Description for firstLine.
     * @serial
     */
	Component lastLine;
     /**
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * Please read Description for firstLine.
     * @serial
     */
	Component firstItem;
    /**
     * A relative positioning constant, that can be used instead of
     * north, south, east, west or center.
     * Please read Description for firstLine.
     * @serial
     */
	Component lastItem;

    /**
     * Constructs a new border layout with
     * no gaps between components.
     */
    public MorphBorderLayout() {
	this(0, 0);
    }

    /**
     * Constructs a border layout with the specified gaps
     * between components.
     * The horizontal gap is specified by <code>hgap</code>
     * and the vertical gap is specified by <code>vgap</code>.
     * @param   hgap   the horizontal gap.
     * @param   vgap   the vertical gap.
     */
    public MorphBorderLayout(int hgap, int vgap) {
        super(hgap,vgap);
    }
    /**
     * Lays out the container argument using this border layout.
     * <p>
     * This method actually reshapes the components in the specified
     * container in order to satisfy the constraints of this
     * <code>BorderLayout</code> object. The <code>NORTH</code>
     * and <code>SOUTH</code> components, if any, are placed at
     * the top and bottom of the container, respectively. The
     * <code>WEST</code> and <code>EAST</code> components are
     * then placed on the left and right, respectively. Finally,
     * the <code>CENTER</code> object is placed in any remaining
     * space in the middle.
     * <p>
     * Most applications do not call this method directly. This method
     * is called when a container calls its <code>doLayout</code> method.
     * @param   target   the container in which to do the layout.
     * @see     java.awt.Container
     * @see     java.awt.Container#doLayout()
     */
    public void layoutContainer(Container target) {
      synchronized (target.getTreeLock()) {
	Insets insets = target.getInsets();
	int top = insets.top;
	int bottom = target.getHeight() - insets.bottom;
	int left = insets.left;
	int right = target.getWidth() - insets.right;
    int center = target.getWidth()/2;
        boolean ltr = target.getComponentOrientation().isLeftToRight();
        Component c = null;

	if ((c=getChild(NORTH,ltr)) != null) {
	    c.setSize(right - left, c.getHeight());
	    Dimension d = c.getPreferredSize();
	    c.setBounds(left, top, right - left, d.height);
	    top += d.height + getVgap();
	}
	if ((c=getChild(SOUTH,ltr)) != null) {
	    c.setSize(right - left, c.getHeight());
	    Dimension d = c.getPreferredSize();
	    c.setBounds(left, bottom - d.height, right - left, d.height);
	    bottom -= d.height + getVgap();
	}
    
	// Center component set to its preferred size. 
	int delta = 0 ;
	if ((c=getChild(CENTER,ltr)) != null) {
	    c.setSize(c.getWidth(), bottom - top);
	    Dimension d = c.getPreferredSize();
	    delta = d.width/2;
	    c.setBounds(center - d.width/2, top, d.width, bottom - top);
	}

	if ((c=getChild(EAST,ltr)) != null) {
	    c.setSize(c.getWidth(), bottom - top);
	    c.setBounds(center+delta, top, center-left-delta, bottom - top);
	}
	if ((c=getChild(WEST,ltr)) != null) {
	    c.setSize(c.getWidth(), bottom - top);
	    c.setBounds(left, top, center-left-delta, bottom - top);
	}
      }
    }

    /**
     * Get the component that corresponds to the given constraint location
     *
     * @param   key     The desired absolute position,
     *                  either NORTH, SOUTH, EAST, or WEST.
     * @param   ltr     Is the component line direction left-to-right?
     */
    private Component getChild(String key, boolean ltr) {
        Component result = null;

        if (key == NORTH) {
            result = (firstLine != null) ? firstLine : north;
        }
        else if (key == SOUTH) {
            result = (lastLine != null) ? lastLine : south;
        }
        else if (key == WEST) {
            result = ltr ? firstItem : lastItem;
            if (result == null) {
                result = west;
            }
        }
        else if (key == EAST) {
            result = ltr ? lastItem : firstItem;
            if (result == null) {
                result = east;
            }
        }
        else if (key == CENTER) {
            result = center;
        }
        if (result != null && !result.isVisible()) {
            result = null;
        }
        return result;
    }

    /**
     * Adds the specified component to the layout, using the specified
     * constraint object.  For border layouts, the constraint must be
     * one of the following constants:  <code>NORTH</code>,
     * <code>SOUTH</code>, <code>EAST</code>,
     * <code>WEST</code>, or <code>CENTER</code>.
     * <p>
     * Most applications do not call this method directly. This method
     * is called when a component is added to a container using the
     * <code>Container.add</code> method with the same argument types.
     * @param   comp         the component to be added.
     * @param   constraints  an object that specifies how and where
     *                       the component is added to the layout.
     * @see     java.awt.Container#add(java.awt.Component, java.lang.Object)
     * @exception   IllegalArgumentException  if the constraint object is not
     *                 a string, or if it not one of the five specified
	 *              constants.
     * @since   JDK1.1
     */
    public void addLayoutComponent(Component comp, Object constraints) {
        super.addLayoutComponent( comp, constraints);
      synchronized (comp.getTreeLock()) {
	if ((constraints == null) || (constraints instanceof String)) {
	    this.addComponent((String)constraints, comp);
	} else {
	    throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
	}
      }
    }

    /**
     * @deprecated  replaced by <code>addLayoutComponent(Component, Object)</code>.
     */
    protected void addComponent(String name, Component comp) {
      synchronized (comp.getTreeLock()) {
	/* Special case:  treat null the same as "Center". */
	if (name == null) {
	    name = "Center";
	}

	/* Assign the component to one of the known regions of the layout.
	 */
	if ("Center".equals(name)) {
	    center = comp;
	} else if ("North".equals(name)) {
	    north = comp;
	} else if ("South".equals(name)) {
	    south = comp;
	} else if ("East".equals(name)) {
	    east = comp;
	} else if ("West".equals(name)) {
	    west = comp;
	} else if (BEFORE_FIRST_LINE.equals(name)) {
	    firstLine = comp;
	} else if (AFTER_LAST_LINE.equals(name)) {
	    lastLine = comp;
	} else if (BEFORE_LINE_BEGINS.equals(name)) {
	    firstItem = comp;
	} else if (AFTER_LINE_ENDS.equals(name)) {
	    lastItem = comp;
	} else {
	    throw new IllegalArgumentException("cannot add to layout: unknown constraint: " + name);
	}
      }
    }

    /**
     * Removes the specified component from this border layout. This
     * method is called when a container calls its <code>remove</code> or
     * <code>removeAll</code> methods. Most applications do not call this
     * method directly.
     * @param   comp   the component to be removed.
     * @see     java.awt.Container#remove(java.awt.Component)
     * @see     java.awt.Container#removeAll()
     */
    public void removeLayoutComponent(Component comp) {
        super.removeLayoutComponent(comp);
      synchronized (comp.getTreeLock()) {
	if (comp == center) {
	    center = null;
	} else if (comp == north) {
	    north = null;
	} else if (comp == south) {
	    south = null;
	} else if (comp == east) {
	    east = null;
	} else if (comp == west) {
	    west = null;
	}
	if (comp == firstLine) {
	    firstLine = null;
	} else if (comp == lastLine) {
	    lastLine = null;
	} else if (comp == firstItem) {
	    firstItem = null;
	} else if (comp == lastItem) {
	    lastItem = null;
	}
      }
    }
}
