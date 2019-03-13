"
I'm the container of moose model understudy
"
Class {
	#name : #MMCMooseModelPresenter,
	#superclass : #MMCAbstractPresenter,
	#instVars : [
		'mooseModels'
	],
	#category : #'Moose-Metric-Calculator-Gui-Presenters'
}

{ #category : #specs }
MMCMooseModelPresenter class >> defaultSpec [
	^ SpecLayout composed
		add: #mooseModels;
		yourself
]

{ #category : #examples }
MMCMooseModelPresenter class >> runMe [
	<example>
	self new openWithSpec
]

{ #category : #initialization }
MMCMooseModelPresenter >> initializeWidgets [
	mooseModels := self instantiate: FastTablePresenter.
]

{ #category : #accessing }
MMCMooseModelPresenter >> mooseModels [
	^ mooseModels
]

{ #category : #subscription }
MMCMooseModelPresenter >> update [
	^ mooseModels items: context mooseModel
]
