package com.springmicroservices.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springmicroservices.rest.SpringMicroservicesApplication;
import com.springmicroservices.rest.controller.AssignmentController;
import com.springmicroservices.rest.service.CheckTriangleService;
import com.springmicroservices.rest.service.CreateSingleArrayService;
import com.springmicroservices.rest.service.FibonacciService;
import com.springmicroservices.rest.service.ReverseStringService;
import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssignmentControllerTest {

	//private static final String Equilateral = "Equilateral";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FibonacciService fibonacciService;
	
	@MockBean
	private CheckTriangleService checkTriangleService;
	
	@MockBean
	private ReverseStringService reverseStringService;
	
	@MockBean
	private CreateSingleArrayService createSingleArrayService;


	@Test
	public void calculateFibonacci() throws Exception {

		Mockito.when(fibonacciService.fibonacciCalculator(Mockito.anyInt())).thenReturn(55);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/Fibonacci/n=10").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "55";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void checkTriangleEquilateral() throws Exception {

		Mockito.when(checkTriangleService.checkTriangleType(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).thenReturn("Equilateral");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/TriangleType/a=1&b=1&c=1").accept(MediaType.ALL);
				/*MediaType.APPLICATION_JSON);*/

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "Equilateral";		
		assertEquals(expected,result.getResponse().getContentAsString());
	}
	
	@Test
	public void checkTriangleIsosceles() throws Exception {

		Mockito.when(checkTriangleService.checkTriangleType(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).thenReturn("Isosceles");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/TriangleType/a=2&b=2&c=1").accept(MediaType.ALL);
				/*MediaType.APPLICATION_JSON);*/

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "Isosceles";		
		assertEquals(expected,result.getResponse().getContentAsString());
	}
	
	@Test
	public void checkTriangleScalene() throws Exception {

		Mockito.when(checkTriangleService.checkTriangleType(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).thenReturn("Scalene");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/TriangleType/a=3&b=2&c=1").accept(MediaType.ALL);
				/*MediaType.APPLICATION_JSON);*/

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "Scalene";		
		assertEquals(expected,result.getResponse().getContentAsString());
	}
	
	@Test
	public void reverseStr() throws Exception {

		Mockito.when(reverseStringService.reverseString(Mockito.anyString())).thenReturn("woh era uoy");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/ReverseWords/sentence=how are you").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "woh era uoy";
		assertEquals(expected,result.getResponse().getContentAsString());
		assertNotEquals(expected, "how are you ");
		
	}
	
	/*@Test
	public void makeOneArray() throws Exception {
		Course mockCourse = new Course("1", "Smallest Number", "1",
				Arrays.asList("1", "2", "3", "4"));
		//JSONObject singleArrayResponse="{\"array\":[1,2,3,4,5,6,11]}";
		// studentService.addCourse to respond back with mockCourse
		Mockito.when(createSingleArrayService.createSingleArray(Mockito.any())).thenReturn("{\"array\":[1,2,3,4,5,6,11]}");

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/makeonearray")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/Student1/courses/1",
				response.getHeader(HttpHeaders.LOCATION));

	}*/
}