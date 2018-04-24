package Controller;

import HibernateEntities.translation.VocabularyWordEntity;
import HibernateEntities.translation.WordDescriptionEntity;
import HibernateEntities.translation.WordTranslationEntity;
import ServiceEntities.VocabularyWordService;
import ServiceEntities.WordDescriptionService;
import ServiceEntities.WordTranslationService;
import com.google.common.base.Strings;
import http.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/vocabulary")
public class VocabularyController {

    @Autowired
    private VocabularyWordService vocabularyWordService;

    @Autowired
    private WordTranslationService wordTranslationService;

    @Autowired
    private WordDescriptionService wordDescriptionService;

    private Logger log = LoggerFactory.getLogger(VocabularyController.class);

    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "/{word}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getWordTranslations(@PathVariable String word) {

        if (Strings.isNullOrEmpty(word)) {
            return new Response("error", HttpStatus.NO_CONTENT);
        }

        VocabularyWordEntity vocabularyWord = vocabularyWordService.findByWord(word.toLowerCase());

        if (vocabularyWord == null) {
            vocabularyWord = vocabularyWordService.createWord(new VocabularyWordEntity(word, new ArrayList<>(), new ArrayList<>()));
        }

        String jsonWord;
        try {
            jsonWord = mapper.writeValueAsString(vocabularyWord);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new Response(jsonWord, HttpStatus.OK);
    }

    @RequestMapping(value = "/{word}/select_translation", method = RequestMethod.PUT)
    public Response selectTranslation(@PathVariable String word, @RequestBody String body) {

        JSONObject request;
        try {
            request = new JSONObject(body);
        } catch (JSONException e) {
            e.printStackTrace();
            return new Response("json_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String translation = request.optString("translation");

        if (Strings.isNullOrEmpty(translation)) {
            return new Response("error", HttpStatus.NO_CONTENT);
        }

        VocabularyWordEntity vocabularyWord = vocabularyWordService.findByWord(word);

        if (vocabularyWord == null) {
            return new Response("error", HttpStatus.NO_CONTENT);
        }

        WordTranslationEntity selectedTranslation = vocabularyWord.getTranslations()
                .stream()
                .filter(t -> t.getTranslation().equals(translation))
                .findFirst()
                .orElse(null);

        if (selectedTranslation == null) {
            return new Response("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        selectedTranslation.increaseRating();
        wordTranslationService.updateTranslation(selectedTranslation);

        String translationJson;

        try {
            translationJson = mapper.writeValueAsString(selectedTranslation);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response("json_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new Response(translationJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/{word}/suggest", method = RequestMethod.POST)
    public Response suggestTranslation(@PathVariable String word, @RequestBody String body) {

        JSONObject request;
        try {
            request = new JSONObject(body);
        } catch (JSONException e) {
            e.printStackTrace();
            return new Response("json_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        VocabularyWordEntity vocabularyWord = vocabularyWordService.findByWord(word);

        String translation = request.optString("translation");
        String description = request.optString("description");

        WordTranslationEntity translationEntity;
        WordDescriptionEntity descriptionEntity;

        if (!Strings.isNullOrEmpty(translation)) {
            translationEntity = wordTranslationService.createTranslation(new WordTranslationEntity(vocabularyWord, translation, null, null)); //last argument should be current student
            vocabularyWord.getTranslations().add(translationEntity);
        }

        if (!Strings.isNullOrEmpty(description)) {
            descriptionEntity = wordDescriptionService.createDescription(new WordDescriptionEntity(vocabularyWord, description, null, null)); //last argument should be current student
            vocabularyWord.getDescriptions().add(descriptionEntity);
        }

        vocabularyWordService.updateWord(vocabularyWord);

        String wordJson;

        try {
            wordJson = mapper.writeValueAsString(vocabularyWord);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response("json_error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new Response(wordJson, HttpStatus.OK);
    }


}
