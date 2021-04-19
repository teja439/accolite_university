package com.accolite.au.controller;

import com.accolite.au.dto.FeedbackDTO;
import com.accolite.au.dto.ProjectFeedbackDTO;
import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.models.ProjectFeedback;
import com.accolite.au.models.Student;
import com.accolite.au.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping({"/add/{batchId}"})
    public ResponseEntity<StudentGroupDTO> addGroup(@Valid @RequestBody StudentGroupDTO studentGroupDTO, @PathVariable(required = true, name = "batchId") int batchId) {
        return new ResponseEntity(groupService.addGroup(studentGroupDTO, batchId), HttpStatus.CREATED);
    }

    @PostMapping({"/automate/{batchId}"})
    public ResponseEntity<List<StudentGroupDTO>> automateGrouping(@PathVariable(required = true, name = "batchId") int batchId, @RequestParam(required = true, name = "groupSize") int groupSize, @RequestBody List<Student> neglectStudentsList) {
        return new ResponseEntity(groupService.automateGrouping(batchId, neglectStudentsList, groupSize), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<StudentGroupDTO>> getAllGroupsForABatch(@RequestParam(required = false, name = "batchId") int batchId){
        return new ResponseEntity(groupService.getAllGroupsForABatch(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{groupId}"})
    public ResponseEntity<StudentGroupDTO> getGroup(@PathVariable(required = true, name = "groupId") int groupId){
        return new ResponseEntity(groupService.getGroup(groupId), HttpStatus.OK);
    }

    @DeleteMapping({"/{groupId}"})
    public ResponseEntity<SuccessResponseDTO> deleteGroup(@PathVariable(required = true, name = "groupId") int groupId){
        return new ResponseEntity(groupService.deleteGroup(groupId), HttpStatus.OK);
    }

    @DeleteMapping({"/{groupId}/{studentId}"})
    public ResponseEntity<SuccessResponseDTO> deleteStudentFromGroup(@PathVariable(required = true, name = "groupId") int groupId, @PathVariable(required = true, name = "studentId") int studentId){
        return new ResponseEntity(groupService.deleteStudentFromGroup(groupId, studentId), HttpStatus.OK);
    }

    @PutMapping({"/appendStudents/{groupId}"})
    public ResponseEntity<StudentGroupDTO> addStudentToGroup(@PathVariable(required = true, name = "groupId") int groupId, @RequestBody List<Student> selectedStudentsList){
        return new ResponseEntity(groupService.addStudentToGroup(groupId, selectedStudentsList), HttpStatus.OK);
    }

    @PutMapping({"/{groupId}"})
    public ResponseEntity<StudentGroupDTO> updateGroup(@Valid @RequestBody StudentGroupDTO studentGroupDTO, @PathVariable(name="groupId") int groupId) {
        return new ResponseEntity(groupService.updateGroup(studentGroupDTO, groupId), HttpStatus.CREATED);
    }

    @PostMapping({"/feedback/{groupId}"})
    public ResponseEntity<FeedbackDTO> submitFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO, @PathVariable(name="groupId") int groupId){
        return new ResponseEntity(groupService.submitFeedback(feedbackDTO, groupId), HttpStatus.CREATED);
    }

    @GetMapping({"/feedback/all/{groupId}"})
    public ResponseEntity<FeedbackDTO> getAllFeedbacks(@PathVariable(name="groupId") int groupId){
        return new ResponseEntity(groupService.getAllFeedbacks(groupId), HttpStatus.OK);
    }

    @GetMapping({"/feedback/{feedbackId}"})
    public ResponseEntity<ProjectFeedbackDTO> getFeedback(@PathVariable(name="feedbackId") int feedbackId){
        return new ResponseEntity(groupService.getFeedback(feedbackId), HttpStatus.OK);
    }

    @GetMapping({"/finalEval/{batchId}"})
    public ResponseEntity<ProjectFeedbackDTO> getAllFinalEvaluationData(@PathVariable(name="batchId") int batchId){
        return new ResponseEntity(groupService.getAllFinalEvaluationData(batchId), HttpStatus.OK);
    }

    @PostMapping({"/finalEval/saveMarks"})
    public ResponseEntity<ProjectFeedbackDTO> assignMarks(@RequestBody ProjectFeedbackDTO projectFeedbackDTO){
        return new ResponseEntity(groupService.assignMarks(projectFeedbackDTO), HttpStatus.OK);
    }
}
