package scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import repositories.TagsRepository;
import tagsDTO.Tags;

@Component
public class TagTrendScheduler {
	private final TagsRepository repository;

	TagTrendScheduler(TagsRepository repository) {
	    this.repository = repository;
	}
	//일정 시간마다 반드시 실행되는 메소드// 단위는 ms (1000ms = 1sec)
	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)//24시간마다 반복된다
	public void resetTrendCounterScheduler() throws Exception {
		List<Tags> list = repository.findAll();
		for(Tags i : list) {
			i.setTrendCounter(0);
			repository.save(i);
		}
	}
}
