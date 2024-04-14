package randomevent;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyDouble;
import player.PlayerProfile;

class DecisionEventTest {
    private static final int ITERATION = 1000;
    private final PlayerProfile playerProfile = mock(PlayerProfile.class);

    @Test
    void triggerEvent_emptyInputAndPlayerReject_modifiesPlayerProfile() {
        DecisionEvent event = new DecisionEvent(1.0);
        for (int i = 0; i < ITERATION; i++) {
            String simulatedInput = "no";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            event.triggerEvent(playerProfile);
        }
        // at least once if return wallet event is triggered
        verify(playerProfile, atLeastOnce()).addAsset(anyInt());
        verify(playerProfile, atLeastOnce()).loseAsset(anyInt());

        verify(playerProfile, never()).adjustAssetMultiplier(anyDouble());
    }

    @Test
    void triggerEvent_emptyInputAndPlayerAccept_modifiesPlayerProfile() {
        DecisionEvent event = new DecisionEvent(1.0);
        for (int i = 0; i < ITERATION; i++) {
            String simulatedInput = "yes";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(inputStream);

            event.triggerEvent(playerProfile);
        }
        verify(playerProfile, atLeastOnce()).addAsset(anyInt());
        verify(playerProfile, atLeastOnce()).loseAsset(anyInt());
        verify(playerProfile, atLeastOnce()).adjustAssetMultiplier(anyDouble());
    }
}
