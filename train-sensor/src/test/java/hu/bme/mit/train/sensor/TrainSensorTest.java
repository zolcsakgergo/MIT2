package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController mockTC;
    TrainUser mockTU;
    TrainSensorImpl trainSensor;


    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockTC, mockTU);
    }

    @Test
    public void overrideSpeedLimit_normal_success(){
        trainSensor.overrideSpeedLimit(50);
        verify(mockTU,never()).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_lessthanzero_alarm(){
        trainSensor.overrideSpeedLimit(-1);
        verify(mockTU).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_more(){
        trainSensor.overrideSpeedLimit(501);
        verify(mockTU).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_less(){
        trainSensor.overrideSpeedLimit(100);
        trainSensor.overrideSpeedLimit(10);
        verify(mockTU).setAlarmState(true);
    }

}
