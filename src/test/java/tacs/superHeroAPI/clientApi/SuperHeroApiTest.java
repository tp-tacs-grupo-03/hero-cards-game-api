package tacs.superHeroAPI.clientApi;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import utn.tacs.common.client.superHeroAPI.clientApi.SuperHeroApi;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Character;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Image;
import utn.tacs.common.client.superHeroAPI.clientApi.model.Powerstats;
import utn.tacs.controllers.exceptions.CannotFoundPowerStats;
import utn.tacs.domain.exceptions.NotFoundCharacter;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuperHeroApiTest {


    @Test
    public void testGetSuperHeroApiById() throws URISyntaxException {
        final SuperHeroApi client = new SuperHeroApi().setToken("10226310284967175");
        Character response = client.getCharacter("1").orElseThrow(()-> new NotFoundCharacter("1"));
        Assert.notNull(response, "cannot get character");
        checkResponse(response);
    }

    private void checkResponse(Character character) {
        assertEquals(character.getId(), "1");
        assertEquals(character.getName(),"A-Bomb" );
        assertEquals(character.getPowerstats().getCombat(),64);
        assertEquals(character.getPowerstats().getDurability(),80);
        assertEquals(character.getPowerstats().getIntelligence(),38);
        assertEquals(character.getPowerstats().getSpeed(),17);
        assertEquals(character.getPowerstats().getStrength(),100);
    }

    @Test
    public void testGetImageById() throws URISyntaxException {
        final SuperHeroApi client = new SuperHeroApi().setToken("10226310284967175");
        ResponseEntity<Image> response = client.getImage("1");
        Assert.notNull(response.getBody(), "cannot get image");
        assertEquals(response.getStatusCode(),HttpStatus.OK );
        Image image = response.getBody();
        assertEquals(image.getUrl(), "https://www.superherodb.com/pictures2/portraits/10/100/10060.jpg");

    }

    @Test
    public void testGetPowerstatsById() throws URISyntaxException {
        final SuperHeroApi client = new SuperHeroApi().setToken("10226310284967175");
        Powerstats powerstats = client.getPowerstats("1").orElseThrow(()-> new CannotFoundPowerStats("1"));
        assertEquals(powerstats.getCombat(),64);
        assertEquals(powerstats.getDurability(),80);
        assertEquals(powerstats.getIntelligence(),38);
        assertEquals(powerstats.getSpeed(),17);
        assertEquals(powerstats.getStrength(),100);

    }
}
