package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore t1;
  TorpedoStore t2;

  @Before
  public void init(){
	  t1 = mock(TorpedoStore.class);
	  t2 = mock(TorpedoStore.class);
    this.ship = new GT4500(t1, t2);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
	  when(t1.isEmpty()).thenReturn(false);
	  when(t2.isEmpty()).thenReturn(false);
	  
	  when(t1.fire(1)).thenReturn(true);
	  when(t2.fire(1)).thenReturn(true);
	  
    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
	  when(t1.getNumberOfTorpedos()).thenReturn(1);
	  when(t2.getNumberOfTorpedos()).thenReturn(1);
	  
	  when(t1.fire(1)).thenReturn(true);
	  when(t2.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedos_Single_FirstIsEmpty_Success() {
	// Arrange
	when(t1.isEmpty()).thenReturn(true);
	when(t2.isEmpty()).thenReturn(false);
		  
	when(t1.fire(1)).thenReturn(false);
	when(t2.fire(1)).thenReturn(true);
		  
	// Act
	boolean result = ship.fireTorpedos(FiringMode.SINGLE);

	// Assert
	assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedos_Single_SecondIsEmpty_Success() {
	// Arrange
	when(t1.isEmpty()).thenReturn(false);
	when(t2.isEmpty()).thenReturn(true);
		  
	when(t1.fire(1)).thenReturn(true);
	when(t2.fire(1)).thenReturn(false);
		  
	// Act
	boolean result = ship.fireTorpedos(FiringMode.SINGLE);

	// Assert
	assertEquals(true, result);
  }
  
  @Test
  public void fireTorpedos_Single_BothIsEmpty_Failure() {
	// Arrange
	when(t1.isEmpty()).thenReturn(true);
	when(t2.isEmpty()).thenReturn(true);
		  
	when(t1.fire(1)).thenReturn(false);
	when(t2.fire(1)).thenReturn(false);
		  
	// Act
	boolean result = ship.fireTorpedos(FiringMode.SINGLE);

	// Assert
	assertEquals(false, result);
  }
  
  @Test
  public void fireTorpedos_All_BothIsEmpty_Failure() {
	// Arrange
	when(t1.getNumberOfTorpedos()).thenReturn(0);
	when(t2.getNumberOfTorpedos()).thenReturn(0);
		  
	when(t1.fire(1)).thenReturn(false);
	when(t2.fire(1)).thenReturn(false);
		  
	// Act
	boolean result = ship.fireTorpedos(FiringMode.ALL);

	// Assert
	assertEquals(false, result);
  }
  
  @Test
  public void fireTorpedos_Single_FirstTorpedoStore_NotFired() {
	// Arrange
	when(t1.isEmpty()).thenReturn(true);
	when(t2.isEmpty()).thenReturn(false);
		  
	when(t2.fire(1)).thenReturn(false);
		  
	// Act
	boolean result = ship.fireTorpedos(FiringMode.SINGLE);

	// Assert
	verify(t1, times(0)).fire(1);
  }
  
  @Test
  public void fireTorpedos_All_FirstTorpedoStore_VerifyNumberOfFires() {
	// Arrange
	when(t1.getNumberOfTorpedos()).thenReturn(3);
	when(t2.getNumberOfTorpedos()).thenReturn(0);
		  
	// Act
	boolean result = ship.fireTorpedos(FiringMode.ALL);

	// Assert
	verify(t1, times(1)).fire(3);
  }

}
