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

}
