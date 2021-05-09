package tacs.superHeroAPI.clientApi;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuperHeroApiTest {

    @Test
    public void testGetSuperHeroApiById() {
        final SuperHeroApi client = new SuperHeroApi();
        ResponseEntity<Character> response = client.getCharacter("1");
        Assert.notNull(response.getBody(), "cannot get character");
        assertEquals(response.getStatusCode(),HttpStatus.OK );
        checkResponse(response);
        ResponseEntity<Character> response2 = client.getCharacter("1");
        checkResponse(response2);
    }

    private void checkResponse(ResponseEntity<Character> response) {
        Character character = response.getBody();
        assertEquals(character.getId(), "1");
        assertEquals(character.getName(),"A-Bomb" );
        assertEquals(character.getPowerstats().getCombat(),64);
        assertEquals(character.getPowerstats().getDurability(),80);
        assertEquals(character.getPowerstats().getIntelligence(),38);
        assertEquals(character.getPowerstats().getSpeed(),17);
        assertEquals(character.getPowerstats().getStrength(),100);
    }

    @Test
    public void testGetImageById() {
        final SuperHeroApi client = new SuperHeroApi();
        ResponseEntity<Image> response = client.getImage("1");
        Assert.notNull(response.getBody(), "cannot get image");
        assertEquals(response.getStatusCode(),HttpStatus.OK );
        Image image = response.getBody();
        assertEquals(image.getUrl(), "https://www.superherodb.com/pictures2/portraits/10/100/10060.jpg");

    }

    @Test
    public void testGetPowerstatsById() {
        final SuperHeroApi client = new SuperHeroApi();
        ResponseEntity<Powerstats> response = client.getPowerstats("1").orElseThrow();
        Assert.notNull(response.getBody(), "cannot get powerstats");
        assertEquals(response.getStatusCode(),HttpStatus.OK );
        Powerstats powerstats = response.getBody();
        assertEquals(powerstats.getCombat(),64);
        assertEquals(powerstats.getDurability(),80);
        assertEquals(powerstats.getIntelligence(),38);
        assertEquals(powerstats.getSpeed(),17);
        assertEquals(powerstats.getStrength(),100);

    }


}
