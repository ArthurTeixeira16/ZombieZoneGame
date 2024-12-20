package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Soldier;
import com.aor.ZombieZone.Model.Spawn;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class SpawnTester {
    private Spawn spawn;
    private Soldier soldier;
    @BeforeEach
    spawn = Mockito.spy(new Spawn(5,5 , ) );
}
