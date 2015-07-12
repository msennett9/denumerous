# Introduction #

Denumerous was initially developed as an intern summer project at a large midwestern insurance company.  Although it's very rudimentary in its current form, it has potential. This document describes some of the directions for development.

# Details #

  1. Additional Generation Algorithms: Pairwise isn't enough.  We need to support 3-way and perhaps even greater strength algorithms.
  1. Specification Language: Right now we use about the simplest input format that could possibly work.  We need to enhance it so we can also specify things like constraints, required combinations, and so on.  YAML has been suggested as a possible extensible input format.  But we may want to create a modeling language of some type like Microsoft's [PICT](http://msdn.microsoft.com/en-us/library/cc150619.aspx).
  1. Improved interface design for combination generators.  Currently, ITestCombinationGenerator simply allows an array of parameters to be passed in and returns a test combination list.  Some generators may need additional inputs (i.e. strength) and also there should be a way to return meta-data about the results such as the results of Allpairs.
  1. Revamped UI: The UI is currently a set of basic Java Server Pages. It will be completely rewritten.  Ideally it would have features that would let you analyze the results, such as the cross reference part of the output from Satisfice's Allpairs tool.